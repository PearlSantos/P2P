import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.Passenger;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public class AdminUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
					frame.start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void start(){	
		Gson gson = new GsonBuilder().create();
		Retrofit retrofit = new Retrofit.Builder()
//			.client(client)
			.baseUrl("http://localhost:9999/") // a legit base url is needed regardless
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();
	//service class
	
		AdminQuery query = retrofit.create(AdminQuery.class);

		Call<ResponseBody> call = query.deletePassenger("9213465468");
		
		/**
		*IDK YET WHERE TO SHOW THE RESULTS OMG
		**/
		Response<ResponseBody> response;
		String reply = "";
		try {
			response = call.execute();
//			reply = response.body().getFullName();
			reply = response.message();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(reply);
		
		
		}
	}
	
	 interface AdminQuery{
		@FormUrlEncoded
		@POST("admin/viewPassengerInfo")
		Call<Passenger> viewPassengerInfo(@Field("mobileNumber")String mobileNumber);
		
		@FormUrlEncoded
		@POST("admin/deletePassenger")
		Call<ResponseBody> deletePassenger(@Field("mobileNumber") String mobileNumber);
		
		@FormUrlEncoded
		@POST("admin/createTrip")
		Call<ResponseBody> createTrip(@Field("tripName") String tripName,
									@Field("ETD") String ETD,
									@Field("ETA") String ETA,
									@Field("routeName") String routeName,
									@Field("plateName") String plateName);
		@FormUrlEncoded
		@POST("admin/updateTrip")
		Call<ResponseBody> updateTrip(@Field("tripName") String tripName,
									@Field("newETD") String newETD,
									@Field("neETA") String newETA);
		@FormUrlEncoded
		@POST("admin/deleteTrip")
		Call<ResponseBody> deleteTrip(@Field("tripName") String tripName);
		
		@FormUrlEncoded
		@POST("admin/sendUpdates")
		Call<ResponseBody> sendUpdates(@Field("message") String message,
									@Body String[] mobileNumbers);
			
	}




	 