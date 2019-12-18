/*
 * Copyright 2008-2009 MOPAS(MINISTRY OF SECURITY AND PUBLIC ADMINISTRATION).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.com.cmm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class HTMLTagFilter implements Filter{

	@SuppressWarnings("unused")
	private FilterConfig config;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String uri = ((HttpServletRequest) request).getRequestURI().toString().trim();
		
		
		String urlList = "/cop/bbs"; // 예외처리할 URL - 여러 개일 경우 별도 처리 필요
		boolean allowedRequest = false;
		// 현재 uri 와 비교
		if (uri.startsWith(urlList)) {
			allowedRequest = true;
		}
		
		if(!allowedRequest) {
			chain.doFilter(new HTMLTagFilterRequestWrapper((HttpServletRequest)request), response);
		} else {
			chain.doFilter(request, (ServletResponse) response); // 필터링을 하지 않을 경우 요청값 변경
		}
		
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {

	}
}
