package org.lockiely.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";



//    @Autowired
//    protected HttpServletRequest request;
//
//    @Autowired
//    protected HttpServletResponse response;
//
//    @Autowired
//    protected HttpSession session;
//
//    @Autowired
//    protected ServletContext application;
//
//    /**
//     * 用户ID
//     */
//    protected Long getCurrentUserId() {
//        return getSSOToken().getId();
//    }
//
//    /**
//     * 返回登录 Token
//     */
//    protected SSOToken getSSOToken() {
//        SSOToken tk = SSOHelper.attrToken(request);
//        if (tk == null) {
//            throw new WebException("-1", "The user does not exist, please relogin.");
//        }
//        return tk;
//    }
//
//    /**
//     * 是否为 post 请求
//     */
//    protected boolean isPost() {
//        return HttpUtil.isPost(request);
//    }
//
//    /**
//     * 是否为 get 请求
//     */
//    protected boolean isGet() {
//        return HttpUtil.isGet(request);
//    }
//
//    /**
//     * <p>
//     * 获取分页对象
//     * </p>
//     */
//    protected <T> Page<T> getPage() {
//        return getPage(10);
//    }
//
//    /**
//     * <p>
//     * 获取分页对象
//     * </p>
//     *
//     * @param size
//     *            每页显示数量
//     * @return
//     */
//    protected <T> Page<T> getPage(int size) {
//        int _size = size, _index = 1;
//        if (request.getParameter("_size") != null) {
//            _size = Integer.parseInt(request.getParameter("_size"));
//        }
//        if (request.getParameter("_index") != null) {
//            _index = Integer.parseInt(request.getParameter("_index"));
//        }
//        return new Page<T>(_index, _size);
//    }
//
//    /**
//     * 重定向至地址 url
//     *
//     * @param url
//     *            请求地址
//     * @return
//     */
//    protected String redirectTo(String url) {
//        StringBuffer rto = new StringBuffer("redirect:");
//        rto.append(url);
//        return rto.toString();
//    }
//
//    /**
//     *
//     * 返回 JSON 格式对象
//     *
//     * @param object
//     *            转换对象
//     * @return
//     */
//    protected String toJson(Object object) {
//        return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
//    }
//
//    /**
//     *
//     * 返回 JSON 格式对象
//     *
//     * @param object
//     *            转换对象
//     * @param format
//     *            序列化特点
//     * @return
//     */
//    protected String toJson(Object object, String format) {
//        if (format == null) {
//            return toJson(object);
//        }
//        return JSON.toJSONStringWithDateFormat(object, format, SerializerFeature.WriteDateUseDateFormat);
//    }
//
//    /**
//     * <p>
//     * 自动判定是否有跨域操作,转成字符串并返回
//     * </p>
//     *
//     * @param object
//     * @return 跨域或不跨域的字符串
//     */
//    protected String callback(ResponseResult object) {
//        return callback(object, null);
//    }
//
//    protected String callback(ResponseResult object, String format) {
//        String callback = request.getParameter("callback");
//        if (callback == null) {
//            /**
//             * 非 JSONP 请求
//             */
//            return toJson(object, format);
//        }
//        StringBuffer json = new StringBuffer();
//        json.append(callback);
//        json.append("(").append(toJson(object, format)).append(")");
//        return json.toString();
//    }
//
//    protected String callbackSuccess(Object obj) {
//        return callback(new ResponseResult(obj));
//    }
//
//    protected String callbackFail(String message) {
//        return callback(new ResponseResult(false, message));
//    }

}
