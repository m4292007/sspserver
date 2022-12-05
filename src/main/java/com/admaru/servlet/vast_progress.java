package com.admaru.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atg.openssp.common.logadapter.LogFacade;

public class vast_progress extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(vast_progress.class);
	private static final long serialVersionUID = 1L;

    public vast_progress() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//LogFacade.logAuction("vast_progress");
	}
}