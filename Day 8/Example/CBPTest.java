package com.cbp;

import matrix.db.*;

class CBPTest
{
	public static void main(String[] args) throws Exception
	{
		try
		{
			Context context = new Context("", "");
			context.setUser("Test Everything");
			context.setPassword("test");
			context.connect();
			System.out.println("Successfully Connected");

			//Examples of Domain API
			fnFindObjectsTest(context);
			fnCreateObject(context);
			fnAddToObject(context);
			fnGetInfo(context);
			fnGetRelatedObjects(context);
			fnSetAttributeValues(context);
			fnGetAllfiles(context);
			fnMQLNotice(context);
			
			//Example of DomainRelationship class
			fnConnect(context);
			
			//Example of Common class
			fnPersonData(context);
			
			context.disconnect();
			System.exit(0);

		}
		catch (Exception objException)
		{
			System.out.println("Exception="+objException.toString());
		}
	}

	public static void fnFindObjectsTest(Context context) throws Exception
	{
		MapList maplist=ne MapList();
		String whereExpression="";
		StringList objectSelects=new StringList();
		objectSelects.add("id");
		objectSelects.add("name");



		maplist=DomainObject.findObjects(context,"Part","*",whereExpression,objectSelects);
		//static MapList findObjects(matrix.db.Context context, java.lang.String typePattern, java.lang.String vaultPattern, java.lang.String whereExpression, matrix.util.StringList objectSelects) 
 
	}

	public static void fnCreateObject(Context context) throws Exception
	{
		//void createObject(matrix.db.Context context, java.lang.String type, java.lang.String name, java.lang.String revision, java.lang.String policy, java.lang.String vault) 
	}
	public static void fnAddToObject(Context context) throws Exception
	{
		// DomainRelationship addToObject(matrix.db.Context context, matrix.db.RelationshipType relationshipType, java.lang.String toObjectId)

		//DomainRelationship createAndConnect(matrix.db.Context context, java.lang.String type, java.lang.String relationshipType, DomainObject parentBO, boolean isFrom) 
 
	}
	public static void fnGetInfo(Context context) throws Exception
	{
		//getInfo(matrix.db.Context context, java.lang.String select) 
	}
	public static void fnGetRelatedObjects(Context context) throws Exception
	{
//		 MapList getRelatedObjects(matrix.db.Context context, java.lang.String relationshipPattern, java.lang.String typePattern, matrix.util.StringList objectSelects, matrix.util.StringList relationshipSelects, boolean getTo, boolean getFrom, short recurseToLevel, java.lang.String objectWhere, java.lang.String relationshipWhere)  
	}
	public static void fnSetAttributeValues(Context context) throws Exception
	{
		/*SimpleDateFormat dateFormat = new SimpleDateFormat(eMatrixDateFormat.getEMatrixDateFormat(),Locale.English);
		
		Date todaysDate = new Date();
		String FormatedDate = dateFormat.format(todaysDate);*/

		//void setAttributeValues(matrix.db.Context context, java.util.Map map)  
	}
	public static void fnGetAllfiles(Context context) throws Exception
	{
//		 MapList getAllFiles(matrix.db.Context context) 
 
	}
	public static void fnMQLNotice(Context context) throws Exception
	{
		/*String strLocale = context.getSession().getLanguage();
		i18nNow i18nNowObj = new i18nNow();
		String strMessage = i18nNowObj.GetString(
				"emxDocumentCentralStringResource", strLocale,
				"emxDocumentCentral.Common.SortAttributeList");
		
		//System.out.println("strMessage=" + strMessage);

		String strMQLCommand = "notice \"" + strMessage + "\"";*/
		
		//static java.lang.String mqlCommand(matrix.db.Context context, java.lang.String command) 
 
	}

	public static void fnConnect(Context context) throws Exception
	{
		//static java.util.Map connect(matrix.db.Context context, DomainObject givenObject, matrix.db.RelationshipType relationshipType, boolean isFrom, java.lang.String[] relatedIds) 
	//static DomainRelationship connect(matrix.db.Context context, DomainObject fromObject, matrix.db.RelationshipType relationshipType, DomainObject toObject) 
	//void setAttributeValue(matrix.db.Context context, java.lang.String attributeName, java.lang.String attributeValue) 
 
	}

	public static void fnPersonData(Context context) throws Exception
	{
		//static java.lang.String createPersonInCompanyVault(matrix.db.Context context, java.lang.String sLoginId, java.lang.String sPassword, java.lang.String roleList, java.lang.String eMail, java.lang.String strCompanyVault) 
		//void addToRole(matrix.db.Context context, java.lang.String role) 
 
		//static boolean doesPersonExists(matrix.db.Context context, java.lang.String sUserId) 
		//static java.lang.String getDisplayName(matrix.db.Context context, java.lang.String personName) 
		//Set Attribute Values
		//matrix.util.StringList getRoleAssignments(matrix.db.Context context) 
		// boolean hasRole(matrix.db.Context context, java.lang.String roleName) 
  
	}

}
