import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ws.rs.FormParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.Passenger;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
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
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AdminUI {
//	private JPanel contentPane;
	private AdminQuery query;
//	private JTextField textField;
	private JLabel label;
	 private JFrame mainFrame;
	 private JPanel controlPanel;
	 private JList list;
	 private JScrollPane scrollPane;
	private JButton btnCreateNewTrip;
	private JButton btnSendUpdates;
	private JDialog dialog;
	private CreateTripPanel ctPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminUI frame = new AdminUI();
//					frame.showListDemo();
//					frame.start();
//					frame.setVisible(true);
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
		start();
		prepareGUI();	
		ctPanel = new CreateTripPanel();
//		tsh = new TripListSelectionHandler();
	}
	
	private void prepareGUI(){
		mainFrame = new JFrame("P2P Admin");
	      mainFrame.setSize(616,456);
	      mainFrame.getContentPane().setLayout(new BorderLayout());
	      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      mainFrame.setBounds(100, 100, 617, 456);
	      
	      controlPanel = new JPanel();
	      controlPanel.setBounds(10, 52, 404, 400);
	      controlPanel.setBorder(new EmptyBorder(15, 5, 5, 5));
	      controlPanel.setLayout(null);

	      btnCreateNewTrip = new JButton("Create New Trip");
	      btnCreateNewTrip.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
//	      		Window win = SwingUtilities.getWindowAncestor(this);
//	            if (win != null) {
	               dialog = new JDialog(mainFrame, "New Trip",
	                        ModalityType.APPLICATION_MODAL);
	               dialog.getContentPane().add(ctPanel);
	               dialog.setPreferredSize(new Dimension(350,300));
	               dialog.pack();
	               dialog.setLocationRelativeTo(null);
	               dialog.setVisible(true);
//	            }
	      		
	      	}
	      });
	      btnCreateNewTrip.setBounds(103, 302, 398, 23);
	      controlPanel.add(btnCreateNewTrip);
	      
	      btnSendUpdates = new JButton("Send Updates");
	      btnSendUpdates.setEnabled(false);
	      btnSendUpdates.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      	}
	      });
	      btnSendUpdates.setBounds(103, 336, 398, 23);
	      controlPanel.add(btnSendUpdates);
	      
	      JPanel north = new JPanel(new FlowLayout(FlowLayout.LEFT));
	      north.setBorder(new EmptyBorder(5, 5, 5, 5));
	      JLabel lblShow = new JLabel("SHOW:");
	      lblShow.setBounds(10, 11, 46, 14);
	      north.add(lblShow);
	      
	      final DefaultListModel df = createModel("Trips");
	      
	      list = new JList(df);
	      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      list.setSelectedIndex(0);
	      list.setVisibleRowCount(10);
	      
	      ListSelectionModel lsm = list.getSelectionModel();
//		  TripListSelectionHandler tsh = new TripListSelectionHandler();
		  lsm.addListSelectionListener(new TripListSelectionHandler());

	      scrollPane = new JScrollPane(list);
	      label = new JLabel("Trips", JLabel.CENTER);
	      label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	      scrollPane.setColumnHeaderView(label);

//	      
//	      scrollPane.setBounds(10, 28, 317, 222);
//	      scrollPane.setBounds(10, 28, 404, 222);
	      scrollPane.setBounds(10, 8, 581, 283);
	      controlPanel.add(scrollPane);
	      
	      String[] listItems = {"Trips", "Passengers"};
	      JComboBox<?> comboBox = new JComboBox<Object>(listItems);
	      comboBox.setBounds(61, 8, 147, 20);
	      comboBox.addActionListener(new ActionListener(){
	
			@Override
			public void actionPerformed(ActionEvent arg0) {
//					String[] temp = createModel(comboBox.getSelectedItem().toString());
					DefaultListModel df = createModel(comboBox.getSelectedItem().toString());
//					ListSelectionModel lsm = list.getSelectionModel();
//					System.out.println("SELECTION: " + lsm.getSelectionMode());
//					TripListSelectionHandler tsh = new TripListSelectionHandler();
				    
					label.setText(comboBox.getSelectedItem().toString());
					scrollPane.setColumnHeaderView(label);
					if(comboBox.getSelectedItem().toString().equals("Trips")){
//						lsm.addListSelectionListener(new TripListSelectionHandler());
						btnCreateNewTrip.setEnabled(true);
						btnSendUpdates.setEnabled(false);
					}
					else{
//						ListSelectionModel lsm = list.getSelectionModel();
//					    lsm.addListSelectionListener(new PassengerListSelectionHandler());
						btnCreateNewTrip.setEnabled(false);
						btnSendUpdates.setEnabled(true);
					}
					list.setModel(df);
					
				}
				
			});
			north.add(comboBox);
			mainFrame.getContentPane().add(north, BorderLayout.NORTH);
	      
//
	      mainFrame.getContentPane().add(controlPanel);
	      mainFrame.setVisible(true);  
	   }

	   private void showListDemo(){                                       

//	      headerLabel.setText("Control in action: JList"); 

	      final DefaultListModel df = createModel("Trips");
	      System.out.println(df.getElementAt(0) + " yehey");
	      list = new JList(df);
	      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      list.setSelectedIndex(0);
	      list.setVisibleRowCount(10);
	      
	      ListSelectionModel lsm = list.getSelectionModel();
//		  TripListSelectionHandler tsh = new TripListSelectionHandler();
		  lsm.addListSelectionListener(new TripListSelectionHandler());

	      scrollPane = new JScrollPane(list);
	      label = new JLabel("Trips", JLabel.CENTER);
	      label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	      scrollPane.setColumnHeaderView(label);

//	      
//	      scrollPane.setBounds(10, 28, 317, 222);
//	      scrollPane.setBounds(10, 28, 404, 222);
	      scrollPane.setBounds(10, 8, 581, 283);
	      controlPanel.add(scrollPane);
	      
	      btnCreateNewTrip = new JButton("Create New Trip");
	      btnCreateNewTrip.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent e) {
//	      		Window win = SwingUtilities.getWindowAncestor(this);
//	            if (win != null) {
	               dialog = new JDialog(mainFrame, "New Trip",
	                        ModalityType.APPLICATION_MODAL);
	               dialog.getContentPane().add(ctPanel);
	               dialog.setPreferredSize(new Dimension(350,300));
	               dialog.pack();
	               dialog.setLocationRelativeTo(null);
	               dialog.setVisible(true);
//	            }
	      		
	      	}
	      });
	      btnCreateNewTrip.setBounds(103, 302, 398, 23);
	      controlPanel.add(btnCreateNewTrip);
	      
	      btnSendUpdates = new JButton("Send Updates");
	      btnSendUpdates.setEnabled(false);
	      btnSendUpdates.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      	}
	      });
	      btnSendUpdates.setBounds(103, 336, 398, 23);
	      controlPanel.add(btnSendUpdates);
//	      mainFrame.setVisible(true);             
	   }
	
	/**
	 * 
	 */
	public void start(){	
		
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();		
				
		
		Gson gson = new GsonBuilder().create();
		Retrofit retrofit = new Retrofit.Builder()
			.client(client)
			.baseUrl("http://localhost:9998/") // a legit base url is needed regardless
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();
	//service class
		query = retrofit.create(AdminQuery.class);
		

//		Call<ResponseBody> call1 = query.retrieveAllTrips();
//		Call<ResponseBody> call2 = query.retrieveAllPassengers();
////		Call<ResponseBody> call3 = query.deleteTrip("Temple Drive to Ateneo (6:15 AM)");
//		
//		/**
//		*IDK YET WHERE TO SHOW THE RESULTS OMG
//		**/
//		Response<ResponseBody> response1;
//		Response<ResponseBody> response2;
//		Response<ResponseBody> response3;
//		String reply = "";
//		try {
//			response1 = call1.execute();
//			System.out.println(response1.message());
//			System.out.println(response1.body().string());
//			response2 = call2.execute();
//			System.out.println(response2.message());
//			System.out.println(response2.body().string());
////			response3 = call3.execute();
////			System.out.println(response3.message());
//			
////			reply = response.body().string();
////			reply = response.message();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(reply);
		
		
		}
	
		public DefaultListModel createModel(String tableName){
			DefaultListModel df = new DefaultListModel();
			Call<ResponseBody> retrieve;
			Response<ResponseBody> result;
			ArrayList<String> list = null;
			String res = "";
			if(tableName.equals("Trips")){
				retrieve = query.retrieveAllTrips();
				
				
			}
			else{
				retrieve = query.retrieveAllPassengers();
				
			}
			
			try {
				result = retrieve.execute();
				res = result.body().string();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			list = parseIntoList(res);
			for(String s: list){
				df.addElement(s);
			}
			
			return df;
		}
		
		public ArrayList<String> parseIntoList(String result){
			ArrayList<String> list = new ArrayList<String>(Arrays.asList(result.split(",")));
			return list;
		}
		
		public String[] returnStringArray(String tableName){
			String[] output;
			Call<ResponseBody> retrieve;
			Response<ResponseBody> result;
			ArrayList<String> list = null;
			String res = "";
			if(tableName.equals("Bus")){
				retrieve = query.retrieveAllBuses();
				
				
			}
			else{
				retrieve = query.retrieveAllRoutes();
				
			}
			
			try {
				result = retrieve.execute();
				res = result.body().string();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			output = res.split(",");
			return output;
			
		}
		
		
		
		
		
		class CreateTripPanel extends JPanel {
			private JTextField tripName;
			private JTextField etd;
			private JTextField eta;
			private JComboBox routes;
			private JComboBox buses;

			/**
			 * Create the panel.
			 */
			public CreateTripPanel() {
				setSize(400,400);
				setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Name:");
				lblNewLabel.setBounds(10, 11, 46, 14);
				add(lblNewLabel);
				
				tripName = new JTextField();
				tripName.setBounds(66, 8, 239, 20);
				add(tripName);
				tripName.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("ETD:");
				lblNewLabel_1.setBounds(10, 48, 46, 14);
				add(lblNewLabel_1);
				
				etd = new JTextField();
				etd.setBounds(66, 45, 86, 20);
				add(etd);
				etd.setColumns(10);
				
				JLabel lblEta = new JLabel("ETA");
				lblEta.setBounds(162, 48, 46, 14);
				add(lblEta);
				
				eta = new JTextField();
				eta.setColumns(10);
				eta.setBounds(219, 45, 86, 20);
				add(eta);
				
				JLabel lblNewLabel_2 = new JLabel("Route:");
				lblNewLabel_2.setBounds(10, 83, 46, 14);
				add(lblNewLabel_2);
				
				
				routes = new JComboBox(returnStringArray("Route"));
				routes.setBounds(66, 80, 239, 20);
				add(routes);
				
				JLabel lblBus = new JLabel("Bus:");
				lblBus.setBounds(10, 114, 46, 14);
				add(lblBus);
				
				buses = new JComboBox(returnStringArray("Bus"));
				buses.setBounds(66, 111, 239, 20);
				add(buses);
				
				JButton btnSave = new JButton("SAVE");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Call<ResponseBody> retrieve;
						Response<ResponseBody> result;
						retrieve = query.createTrip(tripName.getText(), etd.getText(), eta.getText(), routes.getSelectedItem().toString(), buses.getSelectedItem().toString());
						try {
							result = retrieve.execute();
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						
//						DefaultListModel df = createModel("Trips");
//						list.setModel(df);
						
						dialog.dispose();
						
					}
				});
				btnSave.setBounds(66, 142, 198, 23);
				add(btnSave);
				
				JButton btnCancel = new JButton("CANCEL");
				btnCancel.setBounds(66, 176, 198, 23);
				btnCancel.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
						
					}
					
				});
				add(btnCancel);

			}
			
//			public void setValues(String trip_name, String ETD, String ETA, String routeName, String plateNumber){
//				tripName.setText(trip_name);
//				etd.setText(ETD);
//				eta.setText(ETA);
//				routes.setSelectedItem(routeName);
//				buses.setSelectedItem(plateNumber);
//			}
		}
		
		class UpdatePassengerPanel extends JPanel {
			private JTextField fullName;
			private JTextField mobileNumber;
			private JTextField password;
			private JTextField email;
			private JTextField barangay;
			private JTextField city;
			private JTextField zipCode;

			/**
			 * Create the panel.
			 */
			public UpdatePassengerPanel() {
				setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Full Name:");
				lblNewLabel.setBounds(10, 11, 50, 14);
				add(lblNewLabel);
				
				fullName = new JTextField();
				fullName.setEditable(false);
				fullName.setBounds(66, 8, 239, 20);
				add(fullName);
				fullName.setColumns(10);
				
				JButton btnSave = new JButton("SAVE");
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnSave.setBounds(67, 191, 198, 23);
				add(btnSave);
				
				JButton btnCancel = new JButton("CANCEL");
				btnCancel.setBounds(66, 219, 198, 23);
				add(btnCancel);
				
				JLabel lblMobile = new JLabel("Mobile #:");
				lblMobile.setBounds(10, 35, 50, 14);
				add(lblMobile);
				
				mobileNumber = new JTextField();
				mobileNumber.setColumns(10);
				mobileNumber.setBounds(66, 32, 239, 20);
				add(mobileNumber);
				
				JLabel lblPassword = new JLabel("Password:");
				lblPassword.setBounds(10, 59, 50, 14);
				add(lblPassword);
				
				password = new JTextField();
				password.setEditable(false);
				password.setColumns(10);
				password.setBounds(66, 56, 239, 20);
				add(password);
				
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setBounds(10, 83, 50, 14);
				add(lblEmail);
				
				email = new JTextField();
				email.setEditable(false);
				email.setColumns(10);
				email.setBounds(66, 80, 239, 20);
				add(email);
				
				JLabel lblBarangay = new JLabel("Barangay:");
				lblBarangay.setBounds(10, 108, 50, 14);
				add(lblBarangay);
				
				barangay = new JTextField();
				barangay.setEditable(false);
				barangay.setColumns(10);
				barangay.setBounds(66, 105, 239, 20);
				add(barangay);
				
				JLabel lblCity = new JLabel("City:");
				lblCity.setBounds(10, 135, 50, 14);
				add(lblCity);
				
				city = new JTextField();
				city.setEditable(false);
				city.setColumns(10);
				city.setBounds(66, 132, 239, 20);
				add(city);
				
				JLabel lblZipCode = new JLabel("Zip Code:");
				lblZipCode.setBounds(10, 163, 50, 14);
				add(lblZipCode);
				
				zipCode = new JTextField();
				zipCode.setEditable(false);
				zipCode.setColumns(10);
				zipCode.setBounds(66, 160, 239, 20);
				add(zipCode);

			}
			
			public void setValues(String full_name, String _password, String mobile, String e_mail, String _barangay, String _city, String zip_code){
				fullName.setText(full_name);
				mobileNumber.setText(mobile);
				password.setText(_password);
				email.setText(e_mail);
				barangay.setText(_barangay);
				city.setText(_city);
				zipCode.setText(zip_code);
			}
		}
		
		class UpdateTripPanel extends JPanel {
			private JTextField tripName;
			private JTextField etd;
			private JTextField eta;
			private JTextField routes;
			private JTextField buses;

			/**
			 * Create the panel.
			 */
			public UpdateTripPanel() {
				setSize(400,400);
				setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Name:");
				lblNewLabel.setBounds(10, 11, 46, 14);
				add(lblNewLabel);
				
				tripName = new JTextField();
				tripName.setBounds(66, 8, 239, 20);
				tripName.setEditable(false);
				add(tripName);
				tripName.setColumns(10);
				
				JLabel lblNewLabel_1 = new JLabel("ETD:");
				lblNewLabel_1.setBounds(10, 48, 46, 14);
				add(lblNewLabel_1);
				
				etd = new JTextField();
				etd.setBounds(66, 45, 86, 20);
				add(etd);
				etd.setColumns(10);
				
				JLabel lblEta = new JLabel("ETA");
				lblEta.setBounds(162, 48, 46, 14);
				add(lblEta);
				
				eta = new JTextField();
				eta.setColumns(10);
				eta.setBounds(219, 45, 86, 20);
				add(eta);
				
				JLabel lblNewLabel_2 = new JLabel("Route:");
				lblNewLabel_2.setBounds(10, 83, 46, 14);
				add(lblNewLabel_2);
				
				
				routes = new JTextField();
				routes.setBounds(66, 80, 239, 20);
				routes.setEditable(false);
				add(routes);
				
				JLabel lblBus = new JLabel("Bus:");
				lblBus.setBounds(10, 114, 46, 14);
				add(lblBus);
				
				buses = new JTextField();
				buses.setBounds(66, 111, 239, 20);
				buses.setEditable(false);
				add(buses);
				
				JButton btnUpdate = new JButton("UPDATE");
				btnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Call<ResponseBody> retrieve;
						Response<ResponseBody> result;
						ArrayList<String> list = null;
						retrieve = query.updateTrip(tripName.getText(), etd.getText(), eta.getText());
						try {
							result = retrieve.execute();
						} catch (IOException ex) {
							// TODO Auto-generated catch block
							ex.printStackTrace();
						}
						
						dialog.dispose();
						
					}
				});
				btnUpdate.setBounds(66, 142, 198, 23);
				add(btnUpdate);
				
				JButton btnOK = new JButton("OK");
				btnOK.setBounds(66, 176, 198, 23);
				btnOK.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.dispose();
						
					}
					
				});
				add(btnOK);

			}
			
			public void setValues(String trip_name, String ETD, String ETA, String routeName, String plateNumber){
				tripName.setText(trip_name);
				etd.setText(ETD);
				eta.setText(ETA);
				routes.setText(routeName);
				buses.setText(plateNumber);
			}
		}
		
		class TripListSelectionHandler implements ListSelectionListener {
//			String decider = "";
//			public void setDecider(String input){
//				decider = input;
//			}
		    public void valueChanged(ListSelectionEvent e) {
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
//		        
		        	UpdateTripPanel myPanel =  new UpdateTripPanel();
			        
			        Call<ResponseBody> retrieve;
					Response<ResponseBody> result;
					retrieve = query.viewTripDetails((String)list.getModel().getElementAt(lsm.getMinSelectionIndex()));
					System.out.println("LOOK AT ME: " + (String)list.getModel().getElementAt(lsm.getMinSelectionIndex()));
					String res = "";
					try {
						result = retrieve.execute();
						res = result.body().string();
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
					
			   String[] temp = res.split(","); 
			        
			        
			   myPanel.setValues(temp[0], temp[1], temp[2], temp[3], temp[4]);
			        
			        
		        
		       dialog = new JDialog(mainFrame, "View/Edit Trip",
                        ModalityType.APPLICATION_MODAL);
		       
               dialog.getContentPane().add(myPanel);
               dialog.setPreferredSize(new Dimension(350,300));
               dialog.pack();
               dialog.setLocationRelativeTo(null);
               dialog.setVisible(true);
		        

		    	}
		}
		
		class PassengerListSelectionHandler implements ListSelectionListener {
		    public void valueChanged(ListSelectionEvent e) {
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
//		        run Passenger panel
		        
		        UpdatePassengerPanel myPanel =  new UpdatePassengerPanel();
//		        
		        Call<ResponseBody> retrieve;
				Response<ResponseBody> result;
				retrieve = query.viewPassengerInfo((String)list.getModel().getElementAt(lsm.getMinSelectionIndex()));
				System.out.println("LOOK AT ME: " + (String)list.getModel().getElementAt(lsm.getMinSelectionIndex()));
				String res = "";
				try {
					result = retrieve.execute();
					res = result.body().string();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
				String[] temp = res.split(",");
				myPanel.setValues(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
	        	
//	        }
	        
	        dialog = new JDialog(mainFrame, "View/Edit Trip",
                    ModalityType.APPLICATION_MODAL);
	       
           dialog.getContentPane().add(myPanel);
           dialog.setPreferredSize(new Dimension(350,300));
           dialog.pack();
           dialog.setLocationRelativeTo(null);
           dialog.setVisible(true);


		    	}
		}

}
	
	 interface AdminQuery{
		@FormUrlEncoded
		@POST("admin/viewPassengerInfo")
		Call<ResponseBody> viewPassengerInfo(@Field("mobileNumber")String mobileNumber);
		
		@FormUrlEncoded
		@POST("admin/deletePassenger")
		Call<ResponseBody> deletePassenger(@Field("mobileNumber") String mobileNumber);
		
		@FormUrlEncoded
		@POST("admin/createTrip")
		Call<ResponseBody> createTrip(@Field("tripName") String tripName,
									@Field("ETD") String ETD,
									@Field("ETA") String ETA,
									@Field("routeName") String routeName,
									@Field("plateNumber") String plateName);
		@FormUrlEncoded
		@POST("admin/updateTrip")
		Call<ResponseBody> updateTrip(@Field("tripName") String tripName,
									@Field("newETD") String newETD,
									@Field("newETA") String newETA);
		@FormUrlEncoded
		@POST("admin/deleteTrip")
		Call<ResponseBody> deleteTrip(@Field("tripName") String tripName);
		
		@FormUrlEncoded
		@POST("admin/sendUpdates")
		Call<ResponseBody> sendUpdates(@Field("message") String message,
									@Body String[] mobileNumbers);
		
		
		@POST("admin/retrieveAllPassengers")
		Call<ResponseBody> retrieveAllPassengers();
		
		@POST("admin/retrieveAllRoutes")
		Call<ResponseBody> retrieveAllRoutes();
		
		@POST("admin/retrieveAllBuses")
		Call<ResponseBody> retrieveAllBuses();
		
		
		@POST("common/retrieveAllTrips")
		Call<ResponseBody> retrieveAllTrips();
		
		@FormUrlEncoded
		@POST("common/viewTrip")
		Call<ResponseBody> viewTripDetails(@Field("tripName") String tripName);
		
		@FormUrlEncoded
		@POST("common/updateMobileNumber")
		Call<ResponseBody> updateMobileNumber(@Field("oldMobileNumber") String oldMobileNumber,
				@Field("newMobileNumber") String newMobileNumber);
			
	}
	 
	 




	 