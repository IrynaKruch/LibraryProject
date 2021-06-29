package ua.krucheniuk.tag;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.krucheniuk.controller.Servlet;

public class DateTag extends TagSupport {
    private final static Logger log = Logger.getLogger(Servlet.class);

	private static final long serialVersionUID = 8082397665139468773L;

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today = formatter.format(Calendar.getInstance().getTime());
		
		
		try {
			out.print(today);
		} catch (Exception e) {
			log.info(e.getMessage());;
		}
		return SKIP_BODY;
	}
}