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

package org.opencb.cellbase.core.db.api.variation;

import org.opencb.biodata.models.variation.GenomicVariant;
import org.opencb.datastore.core.QueryOptions;
import org.opencb.datastore.core.QueryResult;

import java.util.List;


public interface VariantEffectDBAdaptor {

    @Deprecated
	public QueryResult getAllConsequenceTypesByVariant(GenomicVariant variant, QueryOptions options);

    @Deprecated
	public List<QueryResult> getAllConsequenceTypesByVariantList(List<GenomicVariant> variants, QueryOptions options);
	

	public QueryResult getAllEffectsByVariant(GenomicVariant variant, QueryOptions options);
	
	public  List<QueryResult> getAllEffectsByVariantList(List<GenomicVariant> variants, QueryOptions options);
	
//	public List<GenomicVariantEffect> getAllConsequenceTypeByVariant(GenomicVariant variant);
//	
//	public List<GenomicVariantEffect> getAllConsequenceTypeByVariant(GenomicVariant variant, Set<String> excludeSet);
//
//	
//	public List<GenomicVariantEffect> getAllConsequenceTypeByVariantList(List<GenomicVariant> variants);
//
//	public List<GenomicVariantEffect> getAllConsequenceTypeByVariantList(List<GenomicVariant> variants, Set<String> excludeSet);

	
//	public Map<GenomicVariant, List<GenomicVariantEffect>> getConsequenceTypeMap(List<GenomicVariant> variants);
//
//	public Map<GenomicVariant, List<GenomicVariantEffect>> getConsequenceTypeMap(List<GenomicVariant> variants, Set<String> excludeSet);

}