package pl.edu.pja.jaz.rest;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
		urlPatterns = {"/*"}, 
		initParams = {
				@WebInitParam(name="javax.ws.rs.Application", value="pl.edu.pja.jaz.rest"),
				//@WebInitParam(name="jersey.config.disableMoxyJson.server", value="true")
		}
)
public class RestServlet extends org.glassfish.jersey.servlet.ServletContainer {}
