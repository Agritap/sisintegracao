package com.agritap.sisintegracao.client.request;

import java.util.HashMap;
import java.util.Map;

public class MediaTypes {

	static final MediaTypes instance = new MediaTypes();

	Map<String, Class<?>> type2class = new HashMap<String, Class<?>>();
	Map<Class<?>, String> class2type = new HashMap<Class<?>, String>();

	public MediaTypes() {
		//TODO: Consider using a GWT Generator instead of manual declaration
		registerEntities();
		registerTOs();
	}

	private void registerEntities() {
		register(ProdutorI.TYPE, ProdutorI.class);
//		register(People.TYPE, People.class);
//		register(Person.TYPE, Person.class);
//		register(CourseClass.TYPE, CourseClass.class);
//		register(CourseVersion.TYPE, CourseVersion.class);		
//		register(Roles.TYPE, Roles.class);
//		register(Institution.TYPE, Institution.class);
//		register(Contents.TYPE, Contents.class);
//		register(Enrollment.TYPE, Enrollment.class);
//		register(ActomEntries.TYPE, ActomEntries.class);
//		register(ChatThread.TYPE, ChatThread.class);
//		register(EnrollmentsEntries.TYPE, EnrollmentsEntries.class);
//		register(EnrollmentEntries.TYPE, EnrollmentEntries.class);
	}

	private void registerTOs() {
//		register(PeopleTO.TYPE, PeopleTO.class);
//		register(PersonTO.TYPE, PersonTO.class);
//		register(CoursesTO.TYPE, CoursesTO.class);
//		register(CourseClassesTO.TYPE, CourseClassesTO.class);
//		register(CourseClassTO.TYPE, CourseClassTO.class);		
//		register(CourseVersionsTO.TYPE, CourseVersionsTO.class);
//		register(CourseVersionTO.TYPE, CourseVersionTO.class);
//		register(UserInfoTO.TYPE, UserInfoTO.class);
//		register(UserHelloTO.TYPE, UserHelloTO.class);		
//		register(RolesTO.TYPE, RolesTO.class);
//		register(EnrollmentsTO.TYPE, EnrollmentsTO.class);		
//		register(LibraryFilesTO.TYPE, LibraryFilesTO.class);
//		register(UnreadChatThreadsTO.TYPE, UnreadChatThreadsTO.class);
//		register(ChatThreadMessagesTO.TYPE, ChatThreadMessagesTO.class);
//		register(InstitutionRegistrationPrefixesTO.TYPE,
//				InstitutionRegistrationPrefixesTO.class);
//		register(InstitutionHostNamesTO.TYPE, InstitutionHostNamesTO.class);
//		register(KornellErrorTO.TYPE, KornellErrorTO.class);
//		register(EnrollmentLaunchTO.TYPE, EnrollmentLaunchTO.class);
//		// When auth filter sends 401, it adds the charset and we can't do
//		// anything about it
//		register(KornellErrorTO.TYPE + ";charset=utf-8", KornellErrorTO.class);
//		register(TokenTO.TYPE, TokenTO.class);
//		register(SimplePeopleTO.TYPE, SimplePeopleTO.class);
//		register(SimplePersonTO.TYPE, SimplePersonTO.class);
//		register(InstitutionEmailWhitelistTO.TYPE, InstitutionEmailWhitelistTO.class);
	}

	private void register(String type, Class<?> clazz) {
		type2class.put(type.toLowerCase(), clazz);
		class2type.put(clazz, type.toLowerCase());
	}

	public static MediaTypes get() {
		return instance;
	}

	public Class<?> classOf(String type) {
		return type2class.get(type);
	}

	public String typeOf(Class<?> clazz) {
		return class2type.get(clazz);
	}

	public boolean containsType(String type) {
		return type2class.containsKey(type);
	}

	public boolean containsClass(Class<?> clazz) {
		return class2type.containsKey(clazz);
	}
}
