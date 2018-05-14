package com.sambatech.projeto.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/***
 * 
 * @author Talita
 *
 */

@Service
public class AmazonClient {
	private AmazonS3 client;

	// Endpoint do bucket
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    
    // Nome do bucket
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    
    // Chave de acesso
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    
    // Chave secreta para acesso
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    
    
    /***
     * 	Inicializa do amazon s3, a partir das credenciais geradas 
     */
    @PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.client = AmazonS3ClientBuilder.standard().
    		   withCredentials(new AWSStaticCredentialsProvider(credentials)).
    		   withRegion("us-east-1").build();
       //enableAccelerateMode().
    }
    
    /***
     * Realiza armazenamento do arquivo na Amazon S3
     * @param multipartFile: Arquivo a ser armazenado
     * @return: URL do arquivo na Amazon
     */
    public String uploadFile(Video video) {
    	MultipartFile multipartFile = video.getMultipartFile();
        String fileUrl = "";
        
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile, video.getName());
            fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
            
            uploadFileTos3bucket(fileName, file);
            
            file.delete();            
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }
    
    /***
     * 
     * @return
     */
    public File readFile() {
    	// Le arquivos da s3
    	
    	ListObjectsV2Request req = new ListObjectsV2Request().withBucketName(bucketName);
    	ListObjectsV2Result listing = client.listObjectsV2(req);
    	
    	for (String commonPrefix : listing.getCommonPrefixes()) {
    	        System.out.println(commonPrefix);
    	}
    	for (S3ObjectSummary summary: listing.getObjectSummaries()) {
    	    System.out.println(summary.getKey());
    	}
    	
    	listing.toString();
    	
    	return null;
    }
    
    
    
    /***
     * Constroi nome do arquivo para armazenar no bucket
     * @param multiPart: Arquivo
     * @return nome do arquivo
     */
    private String generateFileName(MultipartFile multiPart, String name) {
        return new Date().getTime() + "-" + name.replace(" ", "_");
    }
    
    /***
     * Insere arquivo no bucket da Amazon S3
     * @param fileName: Nome do arquivo
     * @param file: Arquivo a ser armazenado
     */
    private void uploadFileTos3bucket(String fileName, File file) {
        client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
    
    /***
     * Converte MultipartFile para File
     * @param file arquivo com tipo Multipart
     * @return arquivo com tipo File
     * @throws IOException
     */
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        
        return convFile;
    }   
}