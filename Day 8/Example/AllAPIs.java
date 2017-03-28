package com.cbp;

import com.matrixone.apps.common.Person;
import com.matrixone.apps.domain.DomainConstants;
import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.domain.DomainRelationship;
import com.matrixone.apps.domain.util.MapList;
import com.matrixone.apps.domain.util.MqlUtil;
import com.matrixone.apps.domain.util.PropertyUtil;
import com.matrixone.apps.domain.util.eMatrixDateFormat;
import com.matrixone.apps.domain.util.i18nNow;

import matrix.db.Context;
import matrix.util.StringList;

import java.text.SimpleDateFormat;
import java.util.*;
import matrix.db.*;

class AllAPIs {
	public static void main(String agrs[]) throws Exception {
		try {
			System.out.println("Connecting ....");
			Context context = new Context("", "");
			context.setUser("Test Everything");
			context.setPassword("");
			context.connect();
			System.out.println("Successfully Connected");
			//Examples of Domain API
			fnFindObjectsTest(context);
			//fnCreateObject(context);
			//fnAddToObject(context);
			//fnGetInfo(context);
			//fnGetRelatedObjects(context);
			//fnSetAttributeValues(context);
			//fnGetAllfiles(context);
			//fnMQLNotice(context);
			
			//Example of DomainRelationship class
			//fnConnect(context);
			
			//Example of Common class
			//fnPersonData(context);
			
			

			context.disconnect();
			System.exit(0);

		} catch (Exception objException) {
			System.out.println(objException.toString());
		}
	}

	public static void fnFindObjectsTest(Context context) throws Exception {
		MapList mListPartObj = new MapList();

		String sAttrMaterialCategory = PropertyUtil.getSchemaProperty(context,
				"attribute_MaterialCategory");
		String sRelPartSpecification = PropertyUtil.getSchemaProperty(context,
				"relationship_PartSpecification");

		StringList sListObjSelect = new StringList();
		//Getting basic attributes
		sListObjSelect.add(DomainConstants.SELECT_ID);
		sListObjSelect.add(DomainConstants.SELECT_NAME);
		sListObjSelect.add("type");
		sListObjSelect.add("originated");
		sListObjSelect.add("modified");
		//Getting attribute values
		sListObjSelect.add("attribute[" + sAttrMaterialCategory + "].value");
		//Get attribute of object connected to this object
		sListObjSelect.add("from[" + sRelPartSpecification
				+ "].to.attribute[Model Type].value");

		String strWhere = "attribute[" + sAttrMaterialCategory
				+ "].value == 'Metal'";

		mListPartObj = DomainObject.findObjects(context, "Part", "*", strWhere,
				sListObjSelect);
	
		System.out.println("mListPartObj=" + mListPartObj);

		System.out.println("mListPartObj Size=" + mListPartObj.size());

		Iterator mListPartObjItr = mListPartObj.iterator();

		while (mListPartObjItr.hasNext()) {
			Map mListPartObjMap = (Map) mListPartObjItr.next();

			String strObjName = (String) mListPartObjMap
					.get(DomainConstants.SELECT_NAME);

			System.out.println("strObjName=" + strObjName);

		}

	}

	public static void fnCreateObject(Context context) throws Exception {
		DomainObject doObject = new DomainObject();
		doObject.createObject(context, "Part", "Part1", "1", "EC Part", context
				.getVault().toString());
	}

	public static void fnAddToObject(Context context) throws Exception {

		//Part Object Id
		String strFromObjId = "44665.57881.11582.19153";

		//CAD Drawing Object Id
		String strToObjectId = "44665.57881.37426.42167";

		DomainObject doObject = new DomainObject(strFromObjId);

		RelationshipType relType = new RelationshipType("Part Specification");

		DomainRelationship doRelSpecification = doObject.addToObject(context,
				relType, strToObjectId);

		//doRelSpecification.setAttributeValue(context,"Action","Set Through Program");
		//DomainRelationship doRelSpecification =
		// doObject.addRelatedObject(context,relType,false,strToObjectId);

		/*
		 * DomainObject doDummyObject = new DomainObject(); DomainRelationship
		 * doRelSpecification = doDummyObject.createAndConnect(context, "CAD
		 * Drawing", "Part Specification", doObject, true);
		 */

		System.out.println("Object Connected.....");
	}

	public static void fnGetInfo(Context context) throws Exception {
		String[] objectIds = { "44665.57881.6739.59558",
				"44665.57881.65170.13408" };
		MapList mListPartsData = new MapList();

		StringList objSelects = new StringList();
		objSelects.add(DomainConstants.SELECT_NAME);
		objSelects.add("attribute[Effectivity Date].value");

		mListPartsData = DomainObject.getInfo(context, objectIds, objSelects);

		System.out.println("mListPartsData=" + mListPartsData);

	}

	public static void fnGetRelatedObjects(Context context) throws Exception {
		DomainObject doObjPart = new DomainObject("44665.57881.6739.59558");
		MapList mListRelatedData = new MapList();

		StringList sList = new StringList();
		sList.add(DomainConstants.SELECT_NAME);

		mListRelatedData = doObjPart.getRelatedObjects(context,
				"Part Specification", "CAD Drawing", sList, null, false, true,
				(short) 1, "", "");

		System.out.println("mListRelatedData=" + mListRelatedData);
	}

	public static void fnSetAttributeValues(Context context) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(eMatrixDateFormat
				.getEMatrixDateFormat(), Locale.ENGLISH);

		Date dateToday = new Date();
		String strToday = dateFormat.format(dateToday);
		System.out.println("strToday=" + strToday);
		Map mapAttrData = new HashMap();
		mapAttrData.put("Spare Part", "Yes");
		mapAttrData.put("Effectivity Date", strToday);

		DomainObject doObjPart = new DomainObject("44665.57881.6739.59558");

		doObjPart.setAttributeValues(context, mapAttrData);

		/*
		 * Locale[]ll = Locale.getAvailableLocales();
		 * 
		 * System.out.println("Size="+ll.length); for(int cnt=0;cnt
		 * <ll.length;cnt++) { String lan = ll[cnt].getLanguage();
		 * 
		 * System.out.println("lan="+lan);
		 *  }
		 * 
		 * /*StringList currentAttributeList = new StringList();
		 * currentAttributeList.add("Spare Part");
		 * currentAttributeList.add("Effectivity Date");
		 * currentAttributeList.add("Weight"); currentAttributeList.add("Test");
		 *  
		 */

		/*
		 * StringList sListValData =
		 * doObjPart.validateSelectedAttributes(context,currentAttributeList,
		 * "Spare Part", "Part");
		 * 
		 * System.out.println("sListValData="+sListValData);
		 */

	}

	public static void fnGetAllfiles(Context context) throws Exception {
		DomainObject doObj = new DomainObject("5032.57904.1000.14261");
		MapList mListFiles = doObj.getAllFiles(context);
		System.out.println("mListFiles=" + mListFiles);
	}

	public static void fnPersonData(Context context) throws Exception {
		//Create Person Object
		/*
		 * String strPersonId = Person.createPersonInCompanyVault(context, "Test
		 * CBP","", "role_Employee
		 * role_SalesEngineer","cbp@geometricsoftware.com",
		 * context.getVault().toString());
		 * System.out.println("strPersonId="+strPersonId);
		 */

		//Add TO Role
		/*
		 * Person personObj = new Person("44665.57881.44676.63469");
		 * 
		 * personObj.addToRole(context,"Author");
		 * 
		 * System.out.println("Role Added.....");
		 */

		/*
		 * boolean bExist = Person.doesPersonExists(context, "Test CBP");
		 * System.out.println("bExist="+bExist);
		 */

		//Get Display Name and Set attributes
		/*
		 * String strDisplayName = Person.getDisplayName(context,"Test CBP");
		 * System.out.println("strDisplayName="+strDisplayName);
		 * 
		 * Person cbpPersonObj = new Person("44665.57881.4041.38081"); Map
		 * personDataMap = new HashMap(); personDataMap.put("First
		 * Name","First"); personDataMap.put("Last Name","Last");
		 * cbpPersonObj.setAttributeValues(context,personDataMap);
		 */

		/*
		 * String strLangPref = Person.getLanguagePreference(context,"Test
		 * Everything"); System.out.println("strLangPref="+strLangPref);
		 */

		/*
		 * StringList sListPersonRole = personObj.getRoleAssignments(context);
		 * System.out.println("sListPersonRole="+sListPersonRole);
		 */

		/*
		 * boolean bCheckRole = personObj.hasRole(context,"Author");
		 * System.out.println("bCheckRole="+bCheckRole);
		 */

	}

	public static void fnMQLNotice(Context context) throws Exception {
		String strLocale = context.getSession().getLanguage();
		i18nNow i18nNowObj = new i18nNow();
		String strMessage = i18nNowObj.GetString(
				"emxDocumentCentralStringResource", strLocale,
				"emxDocumentCentral.Common.SortAttributeList");
		
		//System.out.println("strMessage=" + strMessage);

		String strMQLCommand = "notice \"" + strMessage + "\"";
		
		String strReturn = MqlUtil.mqlCommand(context,strMQLCommand);
	}
	
	public static void fnConnect(Context context) throws Exception
	{
		/*DomainObject givenObject = new DomainObject("44665.57881.11582.19153 ");
		RelationshipType relationshipType = new RelationshipType("Part Specification");
		String[] relatedIds = {"44665.57881.37426.42167"};
		Map mapConnect = DomainRelationship.connect(context, givenObject, relationshipType,true,relatedIds);
		
		System.out.println("mapConnect="+mapConnect);*/
		
		DomainRelationship doRel = new DomainRelationship("44665.57881.57073.48604");
		doRel.setAttributeValue(context,"Action","API Class");
		
		System.out.println("Done>>>>>>>");
		 
		
	}
	
}


















