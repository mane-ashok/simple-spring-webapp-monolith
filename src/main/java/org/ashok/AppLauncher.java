package org.ashok;

import javax.servlet.ServletContext;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.ashok.context.ApplicationConfiguration;
import org.ashok.context.WebSecurityConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppLauncher {

	public static void main(String[] args) throws LifecycleException {

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getConnector();

		Context tomcatContext = tomcat.addContext("", null);
		tomcatContext.addFilterDef(getFilterDefination());
		tomcatContext.addFilterMap(getFilterMapping());

		WebApplicationContext appContext = createApplicationContext(tomcatContext.getServletContext());

		/*
		 * add security filter to the servlet context
		 * tomcatContext.getServletContext().addFilter("securityFilter", new
		 * DelegatingFilterProxy("filterChain")).addMappingForUrlPatterns(null, false,
		 * "/*"); //NOTE: above filterChain bean is definded in
		 * WebSecurityConfiguration.class
		 */
		DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

		Wrapper servlet = Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/*");

		tomcat.start();
	}

	private static WebApplicationContext createApplicationContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(ApplicationConfiguration.class);
		ctx.register(WebSecurityConfiguration.class);
		ctx.setServletContext(servletContext);
		ctx.refresh();
		ctx.registerShutdownHook();
		return ctx;
	}

	private static FilterDef getFilterDefination() {
		FilterDef fd = new FilterDef();
		fd.setFilterName("springSecurityFilterChain");
		fd.setFilterClass("org.springframework.web.filter.DelegatingFilterProxy");

		return fd;
	}

	private static FilterMap getFilterMapping() {
		FilterMap fm = new FilterMap();
		fm.setFilterName("springSecurityFilterChain");
		fm.addURLPattern("/*");

		return fm;

	}

}
