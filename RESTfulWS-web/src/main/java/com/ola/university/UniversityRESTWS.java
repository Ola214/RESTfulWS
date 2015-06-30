/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ola.university;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


/**
 *
 * @author Olaa
 */
@Path("/university")
public class UniversityRESTWS {
    
    /*1. @GET
    @Produces(MediaType.TEXT_HTML) //will produce HTML format
    public String getHTMLUniversityInfo(){
        return "<html>"+"<title>"+"University Information"+"</title>"+"<body><h1>"+"NAME - Indian University"+"</body></h1>"+"</html>";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)//will produce plain text format, to sie wyswietli jak wpiszemy http://localhost:8080/RESTfulWS-web/rest/university
    public String getXMLUniversityInfo(){
        return "NAME-Indian University";
    }
    
    @PUT //jezeli updatujemy student information 
    @Path("{studentRollNo}")
    @Produces(MediaType.TEXT_PLAIN) //http://localhost:8080/RESTfulWS-web/rest/university/24
    public String updateUniversityInfo(@PathParam("studentRollNo") String studentRollNo){
        //CODE TO UPDATE STUDENT RECORD USING STUDENT ROLL NO
        return "Cone Successfully!!";
    }*/
    
    /*2. @GET
    @Path("{studentRollNo1}/{studentRollNo2}") //http://localhost:8080/RESTfulWS-web/rest/university/24/44
    @Produces(MediaType.TEXT_PLAIN)//
    public String getStudentsInfo(@PathParam("studentRollNo1") String studentRollNo1, @PathParam("studentRollNo2") String studentRollNo2){
        return "You sent me two roll numbers using pathParam annotation---->"+studentRollNo1+" and "+studentRollNo2;
    }*/
    
    /*3. @GET
    @Produces(MediaType.TEXT_PLAIN)//http://localhost:8080/RESTfulWS-web/rest/university?studentRollNo1=2&studentRollNo2=4
    public String getStudentsInfo(@QueryParam("studentRollNo1") String studentRollNo1, @QueryParam("studentRollNo2") String studentRollNo2){
        return "You sent me two roll numbers using query parameters in http url---->"+studentRollNo1+" and "+studentRollNo2;
    }*/
    
    /*4. @GET//http://localhost:8080/RESTfulWS-web/rest/university;studentRollNo1=23;studentRollNo2=43
    @Produces(MediaType.TEXT_PLAIN)//Parametry moga pojawic sie gdziekolwiek w sciezce
    public String getStudentsInfo(@MatrixParam("studentRollNo1") String studentRollNo1, @MatrixParam("studentRollNo2") String studentRollNo2){
        return "You sent me two roll numbers using matrixParam annotation---->"+studentRollNo1+" and "+studentRollNo2;
    }
    
    @POST
    @Path("/add")
    public String addStudentInfo(@FormParam("name") String name, @FormParam("age") int age){
        return "Web Services has added the students information with name: "+name+" and age: "+age;
    }*/
    
    @GET//http://localhost:8080/RESTfulWS-web/rest/university/get
    @Path("/get") 
    @Produces("text/plain")//pdf - application/pdf, image- image/png
    public Response getStudentFile(){
       File file = new File("d:\\AplikacjeProsteNaukowe\\RESTfulWS\\DemoFile.txt");
       
       ResponseBuilder response = Response.ok((Object)file);
       response.header("Content-Disposition", "attachment; filename=DisplayName-DemoFile.txt");//pod taka nazwa DisplayName-DemoFile.txt pobierzemy plik
       return response.build();
    }
    
    @POST //receive file object from the client
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)//accepting uploaded file from client
    public String uploadFile(@FormDataParam("file") InputStream uploadedInputStream, @FormDataParam("file")FormDataContentDisposition fileDetail){
        //uploadedInputStream holds the File Object, fileDetail holds additional information about the File Object like file name etc.
        //save the file object to the local disk
        saveToDisk(uploadedInputStream, fileDetail);
        return "File uploaded successfully!!";
    }
    
    //save uploaded file to the local disk
    private void saveToDisk(InputStream uploadedInputStream, FormDataContentDisposition fileDetail){
        String uploadedFileLocation = "d://AplikacjeProsteNaukowe/upload/" + fileDetail.getFileName();//specyfying where to save this file
        try{
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            
            out = new FileOutputStream(new File(uploadedFileLocation));
            while((read = uploadedInputStream.read(bytes))!=-1){
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
