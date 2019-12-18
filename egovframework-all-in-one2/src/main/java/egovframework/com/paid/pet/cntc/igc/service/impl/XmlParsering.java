package egovframework.com.paid.pet.cntc.igc.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.star.io.IOException;
 

public class XmlParsering {
 
    public void start() throws Exception{
        URL url = new URL("xml 주소입력");
        URLConnection connection = url.openConnection();
 
        Document doc = parseXML(connection.getInputStream());
        NodeList descNodes = doc.getElementsByTagName("Parent");
 
        for(int i=0; i<descNodes.getLength();i++){
 
            for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
 
                if(node.getNodeName().equals("Child1")){
                    System.out.println(node.getTextContent());
                }else if(node.getNodeName().equals("Child2")){
                    System.out.println(node.getTextContent());
                }else if(node.getNodeName().equals("Child3")){
                    System.out.println(node.getTextContent());
                }
 
            }
 
        }
    }
 
    public Document parseXML(InputStream stream) throws Exception{
 
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
 
        try{
 
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
 
            doc = objDocumentBuilder.parse(stream);
 
        }catch(Exception ex){
            throw ex;
        }       
 
        return doc;
    }
    
	/**
	 * xml request 
	 * @param cmtManageVO 사용자등록정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/uss/cmt/EgovCmtMange.do
	 * @throws MalformedURLException 
	 * @throws Exception
	 */
	public void xmlRequest() throws MalformedURLException {
		try {
            String url = "pass your url";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type",
                    "application/xml;charset=utf-8");
            String urlParameters = "<Request xmlns=\"abc\"><ID>1</ID><Password></Password></Request>";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("response:" + response.toString());
             
        } catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	/**
	 * xml request 
	 * 검색어 : setRequestProperty("Content-Type", "application/xml")
	 * 네이버 https://developers.naver.com/docs/datalab/search/
	 * 통합 검색어 트렌드 API는 네이버 데이터랩의 검색어 트렌드를 API로 실행할 수 있게하는 RESTful API입니다. 주제어로 묶은 검색어들에 대한 네이버 통합검색에서의 검색 추이 데이터를 JSON 형식으로 반환합니다. API를 호출할 때는 주제어와 검색어, 검색 조건을 JSON 형식의 데이터로 전달합니다.
	 * @param  
	 * @return forward:/uss/cmt/EgovCmtMange.do
	 * @throws MalformedURLException 
	 * @throws Exception
	 */
	public void xmlRequestNaver() throws MalformedURLException {
		
		 String clientId = "YOUR_CLIENT_ID";//애플리케이션 클라이언트 아이디값";
	        String clientSecret = "YOUR_CLIENT_SECRET";//애플리케이션 클라이언트 시크릿값";
	        try {
	            String apiURL = "https://openapi.naver.com/v1/datalab/search";
	            String body = "{\"startDate\":\"2017-01-01\",\"endDate\":\"2017-04-30\",\"timeUnit\":\"month\",\"keywordGroups\":[{\"groupName\":\"한글\",\"keywords\":[\"한글\",\"korean\"]},{\"groupName\":\"영어\",\"keywords\":[\"영어\",\"english\"]}],\"device\":\"pc\",\"ages\":[\"1\",\"2\"],\"gender\":\"f\"}";
	            URL url = new URL(apiURL);
	            HttpURLConnection con = (HttpURLConnection)url.openConnection();
	            con.setRequestMethod("POST");
	            con.setRequestProperty("X-Naver-Client-Id", clientId);
	            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
	            con.setRequestProperty("Content-Type", "application/json");

	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(body);
	            wr.flush();
	            wr.close();

	            int responseCode = con.getResponseCode();
	            BufferedReader br;
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	            } else {  // 에러 발생
	                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
	            }

	            String inputLine;
	            StringBuffer response = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                response.append(inputLine);
	            }
	            br.close();
	            System.out.println(response.toString());

	        } catch (Exception e) {
	            System.out.println(e);
	        }
		
		
	}
	
	/**
	 * xml request 
	 * 검색어 : Java - How to post XML request using HttpURLConnection
	 * URL : https://www.boraji.com/java-how-to-post-xml-request-using-httpurlconnection
	 * @param  
	 * @return forward:/uss/cmt/EgovCmtMange.do
	 * @throws java.io.IOException 
	 */
	public void xmlRequestHttp() throws java.io.IOException {
		String request = "<Employee><Name>Sunil</Name></<Employee>";

		URL url = new URL("http://localhost:8080/myservice");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Set timeout as per needs
		connection.setConnectTimeout(20000);
		connection.setReadTimeout(20000);

		// Set DoOutput to true if you want to use URLConnection for output.
		// Default is false
		connection.setDoOutput(true);

		connection.setUseCaches(true);
		connection.setRequestMethod("POST");

		// Set Headers
		connection.setRequestProperty("Accept", "application/xml");
		connection.setRequestProperty("Content-Type", "application/xml");

		// Write XML
		OutputStream outputStream = connection.getOutputStream();
		byte[] b = request.getBytes("UTF-8");
		outputStream.write(b);
		outputStream.flush();
		outputStream.close();

		// Read XML
		InputStream inputStream = connection.getInputStream();
		byte[] res = new byte[2048];
		int i = 0;
		StringBuilder response = new StringBuilder();
		while ((i = inputStream.read(res)) != -1) {
			response.append(new String(res, 0, i));
		}
		inputStream.close();

		System.out.println("Response= " + response.toString());
	}
	
	/**
	 * xml request 
	 * 검색어 : How to pass XML request body in rest post method
	 * URL : https://stackoverflow.com/questions/30862275/java-how-to-pass-xml-request-body-in-rest-post-method
	 * @param  
	 * @return forward:/uss/cmt/EgovCmtMange.do
	 * @throws java.io.IOException 
	 */

	public void xmlReqRestPost()  {
        try {
            String url = "pass your url";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type",
                    "application/xml;charset=utf-8");
            String urlParameters = "<Request xmlns=\"abc\"><ID>1</ID><Password></Password></Request>";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
           // String responseStatus = con.getResponseMessage();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("response:" + response.toString());


        } catch (java.io.IOException e) {
            System.out.println("error" + e.getMessage());
        }
	}
}
