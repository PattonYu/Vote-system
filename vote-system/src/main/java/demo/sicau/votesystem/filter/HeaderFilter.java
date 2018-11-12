package demo.sicau.votesystem.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author PattonYu
 * @date 2018/10/29 22:11
 * Description:
 */
@Order(0) //定义filter执行顺序，数字越小，越先执行
@WebFilter(urlPatterns = "/*",filterName = "HeaderFilter")
public class HeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        // 头部信息
        response.setHeader("Access-Control-Allow-Headers", "token,Origin, X-Requested-With, Content-Type, Accept");

        // 字符编码
        response.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
