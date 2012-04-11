package pl.maciejwalkowiak.plist.spring;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;
import pl.maciejwalkowiak.plist.PlistSerializerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class PlistView extends AbstractView {
	private static final String ENCODING = "UTF-8";

	private PlistSerializerImpl plistSerializer;

	private String contentType = "text/plist";

	private boolean disableCaching = true;

	public PlistView() {
		setContentType(contentType);

		plistSerializer = new PlistSerializerImpl();
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = plistSerializer.toXmlPlist(filterModel(model));

		response.getWriter().write(result);
	}

	@Override
	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType(getContentType());
		response.setCharacterEncoding(ENCODING);
		if (this.disableCaching) {
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");
			response.addDateHeader("Expires", 1L);
		}
	}

	protected Object filterModel(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>(model.size());

		for (Map.Entry<String, Object> entry : model.entrySet()) {
			if (!(entry.getValue() instanceof BindingResult)) {
				result.put(entry.getKey(), entry.getValue());
			}
		}

		return result;
	}

	public void setDisableCaching(boolean disableCaching) {
		this.disableCaching = disableCaching;
	}

	public void setPlistSerializer(PlistSerializerImpl plistSerializer) {
		this.plistSerializer = plistSerializer;
	}
}
