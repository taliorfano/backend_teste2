package com.sambatech.projeto.model;

import java.util.ArrayList;
import java.util.List;

import com.brightcove.zencoder.client.ZencoderClient;
import com.brightcove.zencoder.client.ZencoderClientException;
import com.brightcove.zencoder.client.model.ContainerFormat;
import com.brightcove.zencoder.client.model.State;
import com.brightcove.zencoder.client.request.ZencoderCreateJobRequest;
import com.brightcove.zencoder.client.request.ZencoderOutput;
import com.brightcove.zencoder.client.response.ZencoderCreateJobResponse;
import com.brightcove.zencoder.client.response.ZencoderInputOutputProgress;
import com.brightcove.zencoder.client.response.ZencoderJobDetail;

/***
 * 
 * @author Talita
 *
 */
public class ConvertVideo {
	public ZencoderClient client = new ZencoderClient("3dadf9c8a0a119926fbd16e9efd9ade8");
	
	
	/**
	 * Cria job e converte video utilizando Zencoder
	 * @throws InterruptedException *
	 * 
	 */
	public void CreateJob(String id) throws InterruptedException {
		try {
			ZencoderCreateJobRequest job = new ZencoderCreateJobRequest();
			//job.setInput("s3://zencodertesting/test.mov");
			
			String urlvideo =  "s3://sambanoconvertedvideo.s3-sa-east-1.amazonaws.com/sambanoconvertedvideo/"+
					id+"-exemplo.dv";
			
			job.setInput(urlvideo);
			
			List<ZencoderOutput> outputs = new ArrayList<ZencoderOutput>();
			
			ZencoderOutput output1 = new ZencoderOutput();
			output1.setFormat(ContainerFormat.MP4);
			outputs.add(output1);
	
			ZencoderOutput output2 = new ZencoderOutput();
			output2.setFormat(ContainerFormat.WEBM);
			outputs.add(output2);
	
			job.setOutputs(outputs);		
			ZencoderCreateJobResponse response = client.createZencoderJob(job);
			
			String jobId = response.getId();
			ZencoderJobDetail details = client.getZencoderJob(jobId);
			String inputId = details.getInputMediaFile().getId();
			String outputId1 = response.getOutputs().get(0).getId();
			String outputId2 = response.getOutputs().get(1).getId();
			    
			boolean ready = false;
			
			while (!ready) {
				ZencoderInputOutputProgress inputProgress = client.getInputProgress(inputId);
				ZencoderInputOutputProgress outputProgress1 = client.getOutputProgress(outputId1);
				ZencoderInputOutputProgress outputProgress2 = client.getOutputProgress(outputId2);
	            
	            if (inputProgress.getState().equals(State.FINISHED) &&
	            	outputProgress1.getState().equals(State.FINISHED) && 
	            	outputProgress2.getState().equals(State.FINISHED) ) { 
	            	ready = true; 
	            } 
	            Thread.sleep(1000);
	            // Colocar alguma mensagem
	        }
			
			if(ready){
				// Salva na S3 no bucket de videos convertidos
			}
			
		} catch (ZencoderClientException e) {

			// TODO
			e.printStackTrace();
		}
	}
	
	public void SearchJog() {
		
	}

}
