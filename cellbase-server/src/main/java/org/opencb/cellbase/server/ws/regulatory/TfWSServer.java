/*
 * Copyright 2015 OpenCB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.opencb.cellbase.server.ws.regulatory;

import com.google.common.base.Splitter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.opencb.cellbase.core.db.api.regulatory.TfbsDBAdaptor;
import org.opencb.cellbase.server.exception.SpeciesException;
import org.opencb.cellbase.server.exception.VersionException;
import org.opencb.datastore.core.QueryResponse;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Path("/{version}/{species}/regulatory/tf")
@Produces("text/plain")
@Api(value = "TFBS", description = "Gene RESTful Web Services API")
public class TfWSServer extends RegulatoryWSServer {

	public TfWSServer(@PathParam("version") String version, @PathParam("species") String species, @Context UriInfo uriInfo, @Context HttpServletRequest hsr) throws VersionException, SpeciesException, IOException {
		super(version, species, uriInfo, hsr);
	}

	
	
//	@GET
//	@Path("/{tfId}/info")
//	public Response getTfInfo(@PathParam("tfId") String query) {
//		try {
//			parseQueryParams();
//			ProteinDBAdaptor adaptor = dbAdaptorFactory.getProteinDBAdaptor(this.species, this.assembly);
//			return generateResponse(query, adaptor.getAllByGeneNameList(Splitter.on(",").splitToList(query)));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return createErrorResponse("getTfInfo", e.toString());
//		}
//	}
	

//	@GET
//	@Path("/{tfId}/fullinfo") // Devuelve los TFBSs para el TFId que le das
//	public Response getTfFullInfo(@PathParam("tfId") String query) {
//		try {
//			parseQueryParams();
//			ProteinDBAdaptor proteinDBAdaptor = dbAdaptorFactory.getProteinDBAdaptor(this.species, this.assembly);
//			GeneDBAdaptor geneDBAdaptor = dbAdaptorFactory.getGeneDBAdaptor(this.species, this.assembly);
//			TranscriptDBAdaptor transcriptDBAdaptor = dbAdaptorFactory.getTranscriptDBAdaptor(this.species, this.assembly);
//			TfbsDBAdaptor tfbsDBAdaptor = dbAdaptorFactory.getTfbsDBAdaptor(this.species, this.assembly);
//
//			List<List<Gene>> geneListList = geneDBAdaptor.getAllByTfNameList(Splitter.on(",").splitToList(query));
//			List<String> ensemblGeneList = new ArrayList<String>();
//			List<String> externalNameList = new ArrayList<String>();
//			for(List<Gene> geneList : geneListList) {
//				if(geneList != null && geneList.size() > 0) {
//					ensemblGeneList.add(geneList.get(0).getId());
//					externalNameList.add(geneList.get(0).getName());
//				}else {
//					ensemblGeneList.add("");
//					externalNameList.add("");
//				}
//			}
//
//			List<List<Protein>> proteinList = proteinDBAdaptor.getAllByGeneNameList(externalNameList);
//			List<List<Transcript>> transcriptList = transcriptDBAdaptor.getAllByProteinNameList(externalNameList);
//			List<List<Gene>> targetGeneList = geneDBAdaptor.getAllByTfList(Splitter.on(",").splitToList(query));
////			List<List<Pwm>> pwmGeneList =  tfbsDBAdaptor.getAllPwmByTfGeneNameList(Splitter.on(",").splitToList(query)));
//
//			List<List<DbReferenceType>> proteinXrefList = proteinDBAdaptor.getAllProteinXrefsByProteinNameList(externalNameList);
//			List<List<FeatureType>> proteinFeature = proteinDBAdaptor.getAllProteinFeaturesByProteinXrefList(externalNameList);
//
//			StringBuilder response = new StringBuilder();
//			response.append("[");
//			for (int i = 0; i < geneListList.size(); i++) {
//				if(geneListList.get(i).size() > 0){
//					response.append("{");
////					response.append("\"proteins\":"+gson.toJson(proteinList.get(i))+",");
////					response.append("\"gene\":"+gson.toJson(geneListList.get(i).get(0))+",");
////					response.append("\"transcripts\":"+gson.toJson(transcriptList.get(i))+",");
////					response.append("\"pwm\":"+gson.toJson(pwmGeneList.get(i))+",");
////					response.append("\"targetGenes\":"+gson.toJson(targetGeneList.get(i))+",");
////					response.append("\"protein_xref\":"+gson.toJson(proteinXrefList.get(i))+",");
////					response.append("\"protein_feature\":"+gson.toJson(proteinFeature.get(i))+"");
//					response.append("},");
//				}else{
//					response.append("null,");
//				}
//			}
//			response.append("]");
//			//Remove the last comma
//			response.replace(response.length()-2, response.length()-1, "");
//
//			return  generateResponse(query,Arrays.asList(response));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			return createErrorResponse("getTfFullInfo", e.toString());
//		}
//	}
	
	@GET
	@Path("/{tfId}/tfbs")
    @ApiOperation(httpMethod = "GET", value = "Retrieves all the gene objects", response = QueryResponse.class, notes = "This is a note")
	public Response getAllByTfbs(@ApiParam(name = "pepe", required = true, allowableValues = "a,b,c", value = "aaaaa") @PathParam("tfId") String query, @DefaultValue("")@QueryParam("celltype") String celltype, @DefaultValue("-2500")@QueryParam("start") String start, @DefaultValue("500")@QueryParam("end") String end) {
		try {
			parseQueryParams();
			TfbsDBAdaptor adaptor = dbAdaptorFactory.getTfbsDBAdaptor(this.species, this.assembly);
			if(celltype == null || celltype.equals("")) {
				celltype = null;
			}
			int iStart = -2500;
			int iEnd = 500;
			try {
				if (start != null && end  != null) {
					iStart = Integer.parseInt(start);
					iEnd = Integer.parseInt(end);
				}
			}catch(NumberFormatException e) {
				e.printStackTrace();
				return createErrorResponse("getAllByTfbs", e.toString());
			}
//			return createOkResponse(adaptor.getAllByChromosomeIdList(Splitter.on(",").splitToList(query)), celltype, iStart, iEnd));
			return createOkResponse(adaptor.getAllByIdList(Splitter.on(",").splitToList(query), queryOptions));
//			return generateResponse(query, adaptor.getAllByTfGeneNameList(Splitter.on(",").splitToList(query))));
		} catch (Exception e) {
			e.printStackTrace();
			return createErrorResponse("getAllByTfbs", e.toString());
		}
	}
	
	
//	@GET
//	@Path("/{tfId}/gene")
//	public Response getEnsemblGenes(@PathParam("tfId") String query) {
//		try {
//			parseQueryParams();
//			GeneDBAdaptor geneDBAdaptor = dbAdaptorFactory.getGeneDBAdaptor(this.species, this.assembly);
//			return  generateResponse(query, "GENE", geneDBAdaptor.getAllByTfList(Splitter.on(",").splitToList(query)));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return createErrorResponse("getEnsemblGenes", e.toString());
//		}
//	}
//
//	@GET
//	@Path("/{tfId}/target_gene")
//	public Response getTargetGenes(@PathParam("tfId") String query) {
//		try {
//			parseQueryParams();
//			GeneDBAdaptor geneDBAdaptor = dbAdaptorFactory.getGeneDBAdaptor(this.species, this.assembly);
//			return  generateResponse(query, "GENE", geneDBAdaptor.getAllTargetsByTfList(Splitter.on(",").splitToList(query)));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return createErrorResponse("getEnsemblGenes", e.toString());
//		}
//	}
	
//	@GET
//	@Path("/{tfId}/pwm")
//	public Response getAllPwms(@PathParam("tfId") String query) {
//		try {
//			parseQueryParams();
//			TfbsDBAdaptor tfbsDBAdaptor = dbAdaptorFactory.getTfbsDBAdaptor(this.species, this.assembly);
//			return generateResponse(query, tfbsDBAdaptor.getAllPwmByTfGeneNameList(Splitter.on(",").splitToList(query))));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return createErrorResponse("getAllPwms", e.toString());
//		}
//	}
	
	
	@GET
	@Path("/annotation")
	public Response getAnnotation(@DefaultValue("")@QueryParam("celltype") String celltype) {
		try {
			parseQueryParams();
			TfbsDBAdaptor tfbsDBAdaptor = dbAdaptorFactory.getTfbsDBAdaptor(this.species, this.assembly);
			List<Object> results;
			if (celltype.equals("")){
				results = tfbsDBAdaptor.getAllAnnotation();
			}
			else{
				results = tfbsDBAdaptor.getAllAnnotationByCellTypeList(Splitter.on(",").splitToList(celltype));
			}
			List<String> lista = new ArrayList<String>();			
			
			for (Object result : results) {
				lista.add(((Object [])result)[0].toString()+"\t" + ((Object [])result)[1].toString());
			}
			return  generateResponse(new String(), lista);
			
		} catch (Exception e) {
			e.printStackTrace();
			return createErrorResponse("getAnnotation", e.toString());
		}
	}

	@GET
	public Response defaultMethod() {
		return help();
	}
	
	@GET
	@Path("/help")
	public Response help() {
		StringBuilder sb = new StringBuilder();
		sb.append("Input:\n");
		sb.append("All id formats are accepted.\n\n\n");
		sb.append("Resources:\n");
		sb.append("- info: Get information about this transcription factor (TF).\n\n");
		sb.append("- tfbs: Get all transcription factor binding sites (TFBSs) for this TF.\n");
		sb.append(" Output columns: TF name, target gene name, chromosome, start, end, cell type, sequence, score.\n\n");
		sb.append("- gene: Get all genes regulated by this TF.\n");
		sb.append(" Output columns: Ensembl gene, external name, external name source, biotype, status, chromosome, start, end, strand, source, description.\n\n");
		sb.append("- pwm: Get all position weight matrices associated to this TF.\n");
		sb.append(" Output columns: TF Name, type, frequency_matrix, description, source, length, jaspar_accession.\n\n\n");
		sb.append("Documentation:\n");
		sb.append("http://docs.bioinfo.cipf.es/projects/cellbase/wiki/Regulatory_rest_ws_api#Transcription-Factor");
		
		return createOkResponse(sb.toString());
	}
}
