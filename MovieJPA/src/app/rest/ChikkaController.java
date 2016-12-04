package app.rest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import RetrofitPostTester.JDICTService;
//import ChikkaUI.JDICTService;
import app.entities.Movie;
import app.repositories.MovieRepository;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

@Component
@Path("chikka")
public class ChikkaController {
	
	@Autowired
	private MovieRepository dao;
	
	@GET
	@Path("/getMovie")
	@Produces(MediaType.TEXT_PLAIN)
	public String getMovie(@QueryParam ("title") String title, @QueryParam ("number") String mobileNumber ) throws IOException
	{
		System.out.print("Hello");
		
		Movie m = dao.findByTitle(title);
		Double gross = m.getGross();
		String result =  m.getTitle() + " grossed $" + gross;
		
//		return result;
		
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		Gson gson = new GsonBuilder().create();
		java.net.Proxy proxy = new Proxy(Proxy.Type.HTTP,  new InetSocketAddress("proxy.admu.edu.ph", 3128));
	    OkHttpClient client = new OkHttpClient.Builder()
					.proxy(proxy)
					.build();
		
		
//		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
		
		Retrofit retrofit = new Retrofit.Builder()
				.client(client)
				.baseUrl("http://localhost:9999/") // a legit base url is needed regardless
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		
		JDICTService service = retrofit.create(JDICTService.class);
		
		
		String id = String.valueOf(UUID.randomUUID()).replaceAll("-","");

		Call<ResponseBody> call = service.requestTranslation("SEND", mobileNumber, "292900116", id, result, "ccf10e428638e2479eb551642a7a27f134b07b1d65c3c8d5e6194899cbff7193", "54df09dfcb09927c24b078406768ed02cb8d66207ce567f2bab48128768dba12");
		
		Response<ResponseBody> response = call.execute();
		
		String reply = response.body().string();
		
		System.out.println(reply);
		return reply;
		
		
		
	}
		
	private interface JDICTService
	{
		// this is what is supposed to be sent to the server, 3 fields
		//String data = "gloss_line="+URLEncoder.encode("今日先生","UTF-8")+"&dicsel=9&glleng=60";
		
		@FormUrlEncoded
		@POST("https://post.chikka.com/smsapi/request")
		Call<ResponseBody> requestTranslation(@Field("message_type") String param1, 
											  @Field("mobile_number") String param2, 
											  @Field("shortcode") String param3,
											  @Field("message_id") String param4,
											  @Field("message") String param5, 
											  @Field("client_id") String param6,
											  @Field("secret_key") String param7);
	}

	}



