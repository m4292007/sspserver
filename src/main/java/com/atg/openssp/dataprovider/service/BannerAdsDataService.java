package com.atg.openssp.dataprovider.service;

import com.atg.openssp.common.core.system.LocalContext;
import com.atg.openssp.common.exception.RequestException;
import com.atg.openssp.common.provider.DataHandler;
import com.atg.openssp.dataprovider.provider.handler.BannerAdDataHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Servlet implementation class BannerVideoService
 *
 * @author Brian Sorensen
 */
@WebServlet(value = BannerAdDataHandler.CONTEXT, asyncSupported = false, name = "BannerAdData-Service")
public class BannerAdsDataService extends CoreDataServlet<DataHandler> {

	private static final long serialVersionUID = 1L;

	@Override
	protected DataHandler getHandler(final HttpServletRequest request, final HttpServletResponse response) throws RequestException {
		String handlerClassName = LocalContext.getBannerAdDataHandlerClass();
		if (handlerClassName == null) {
			return new BannerAdDataHandler(request, response);
		} else {
			try {
				Class handlerClass = Class.forName(handlerClassName);
				Constructor cc = handlerClass.getConstructor(new Class[]{HttpServletRequest.class, HttpServletResponse.class});
				return (DataHandler) cc.newInstance(new Object[]{request, response});
			} catch (ClassNotFoundException e) {
				throw new RequestException("missing: "+LocalContext.getBannerAdDataHandlerClass());
			} catch (NoSuchMethodException e) {
				throw new RequestException(e.getMessage());
			} catch (IllegalAccessException e) {
				throw new RequestException(e.getMessage());
			} catch (InstantiationException e) {
				throw new RequestException(e.getMessage());
			} catch (InvocationTargetException e) {
				throw new RequestException(e.getMessage());
			}
		}
	}

}
