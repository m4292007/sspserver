package com.admaru.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atg.openssp.common.logadapter.LogFacade;

public class vast_skip extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(vast_skip.class);
	private static final long serialVersionUID = 1L;

    public vast_skip() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//LogFacade.logAuction("vast_skip");
	}
}