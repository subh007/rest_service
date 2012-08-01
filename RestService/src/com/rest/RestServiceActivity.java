package com.rest;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.rest.R.id;

import android.app.Activity;
import android.os.Bundle;
import android.sax.Element;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RestServiceActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       Button b=(Button)findViewById(id.my_button);
       b.setOnClickListener(this);
       EditText et1=(EditText) findViewById(id.my_req);
       et1.setText("http://where.yahooapis.com/geocode?location=701+First+Ave,+Sunnyvale,+CA&36zC6xzV34EPtVWHx4MiOjnWSh7fDCFD0QouTSqxIksrGc9MT3D2E7SlxXmyYUefehwAXGxbfYKbbIqZutMGzx21TVWGDng");
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button b=(Button) findViewById(id.my_button);
		//b.setClickable(false);
		String str="empty";
		HttpClient httpclient= new DefaultHttpClient();
		HttpContext context= new BasicHttpContext();
		EditText et1=(EditText) findViewById(id.my_req);
		
		
		HttpGet httpGet=new HttpGet(et1.getText().toString());
		try{
			HttpResponse resp= httpclient.execute(httpGet,context);
			HttpEntity entity= resp.getEntity();
			
			if(resp.getStatusLine().getStatusCode()==200)
			{
				
				DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
				DocumentBuilder db= factory.newDocumentBuilder();
				InputSource source=new InputSource();
				source.setCharacterStream(new StringReader(EntityUtils.toString(entity)));
				Document doc=db.parse(source);
				
			   NodeList nl=doc.getElementsByTagName("latitude");
			   //str="inside try";
			   org.w3c.dom.Element e=(org.w3c.dom.Element)nl.item(0);
			   str= e.getTextContent();
			   
			   nl=doc.getElementsByTagName("longitude");
			   //str="inside try";
			    e=(org.w3c.dom.Element)nl.item(0);
			   String str1= e.getTextContent();
			   //str="inside try";
			   EditText et=(EditText) findViewById(id.my_edit);
				et.setText("latitude : "+str  + "\nlongitude :" +str1);
				
			}
			System.out.println("dfdfs");
					/*if(entity != null)
					{
					str = EntityUtils.toString(entity);
					                 
					 }*/
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
    
   
}