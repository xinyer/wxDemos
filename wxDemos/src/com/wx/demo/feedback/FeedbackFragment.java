package com.wx.demo.feedback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wx.demo.R;

/** 
 * @author browserwang 
 * @version 2014年8月20日 下午3:21:05 
 * 类说明 
 */
public class FeedbackFragment extends Fragment implements OnClickListener{

	private static final int FID = 1055;
	private static final int YK = 1;//0：非游客，必须带QQ号码；1：游客
	private static final String CGI_ERR_RET = "-2014";
	
	EditText editText;
	Button button;
	TextView textView;
	ImageView imageView;
	AlphaAnimation animation;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.feedback, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		editText = (EditText) view.findViewById(R.id.fb_et);
		button = (Button) view.findViewById(R.id.fb_btn);
		textView = (TextView) view.findViewById(R.id.fb_tv);
		button.setOnClickListener(this);
		imageView = (ImageView) view.findViewById(R.id.fb_iv);
		animation = (AlphaAnimation)AnimationUtils.loadAnimation(getActivity(), R.anim.alpha_anim);
		imageView.startAnimation(animation);
	}
	
	private String getCgiURL(String content) {
		String title = "Android_feedback_qcallid:[from:wxdemos]";
		
		String deviceInfo = "device:WXDemos device";
		
		try {
			title = URLEncoder.encode(title, "UTF-8");
			deviceInfo = URLEncoder.encode(deviceInfo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		
		return getCgiURL(title, content, deviceInfo);
	}
	
	private String getCgiURL(String title, String content, String deviceInfo) {
		String contentUTF8 = null;
		try {
			contentUTF8 = URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			
		}
		
		String url = "http://support.qq.com/cgi-bin/addpost?qq=&title=%s&text=%s&fid=%d&yk=%d&ip=&info=%s";
		url = String.format(url, title, contentUTF8, FID, YK, deviceInfo);
		
		return url;
	}
	
	class FeedbackPostTask extends AsyncTask<String, String, String>{

	    @Override
	    protected String doInBackground(String... uri) {
	        
	        HttpParams params = new BasicHttpParams();
	        HttpConnectionParams.setConnectionTimeout(params, 5000);
	        HttpConnectionParams.setSoTimeout(params, 5000);
	        
	        HttpClient httpclient = new DefaultHttpClient(params);
	        
	        HttpResponse response;
	        String responseString = null;
	        try {
	            response = httpclient.execute(new HttpGet(uri[0]));
	            StatusLine statusLine = response.getStatusLine();
	            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	                ByteArrayOutputStream out = new ByteArrayOutputStream();
	                response.getEntity().writeTo(out);
	                out.close();
	                responseString = out.toString();
	            } else{
	                response.getEntity().getContent().close();
	                throw new IOException(statusLine.getReasonPhrase());
	            }
	        } catch (ClientProtocolException e) {
	        	e.printStackTrace();
	        } catch (UnsupportedEncodingException e) {
	        	e.printStackTrace();
			} catch (IOException e) {
	        	e.printStackTrace();
	        }
	        return responseString;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	        super.onPostExecute(result);
	       
	        /*if (result!=null && result.length()>0) {
	        	String ret = parseXml(result);
		        if (ret.equals("0")) {//success
					
				} else {
				}
			} else {
				//超时
			}*/
	        textView.setText(result);
	       
	    }
	    
	}
	
	private String parseXml(String xml) {
		int start = xml.indexOf("<er>") + "<er>".length();
		int end = xml.indexOf("</er>");
		if (start<end) {
			return xml.substring(start, end);	
		}
		return CGI_ERR_RET;//弱网环境返回xml，有可能解析end<start,自定义返回码
	}
	
	@Override
	public void onClick(View v) {
		String content = editText.getText().toString().trim();
	    String url = getCgiURL(content);
	    new FeedbackPostTask().execute(url);
	}
}
