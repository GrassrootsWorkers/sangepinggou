<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Stylesheets -->
    <!--#include virtual="/html/header/common_header.html"-->
    <link href="http://s.sangepg.com/css/personal.css?t=1" type="text/css">
    <script src="http://s.sangepg.com/js/jquery/jquery-3.0.0.js"/>

    <script src="http://s.sangepg.com/js/jquery/jquery.form.js" type="text/javascript"></script>
    <script src="http://s.sangepg.com/js/ajaxfileupload.js" type="text/javascript"></script>

</head>

<body class="bgf6">
<script type="text/javascript">
</script>
<!-- Container -->
<div class="main">
    <!--#include virtual="/html/header/header.html"-->
    <!--#include virtual="/html/user/user_info.html"-->

    <div class="content bg pb120">
        <form method="post" id="save_user_info" action="/admin/user/info/">
            <div class="personal_top_nav">
                <ul>
                    <li class="h40">
                        <a href="http://www.binggou.com/user/project/list/1"> &lt;返回个人中心</a>
                        <a href="http://www.binggou.com/help/list" class="r_help"><i></i>帮助中心</a>
                    </li>
                    <li class="h70 w540 li_service">
                    </li>
                    <li class="h70 w540">
                        <h1>设置头像</h1>
                        <a href="javascript:;" class="div_file">编辑
                            <input type="file" id="imgFile" name="imgFile" class="div_file" onchange="imageChange()">
                        </a>

                    </li>

                    <li class="h70 w540">
                        <h1>密码修改</h1>
                        <a class="btn_r" href="javascript:;" onclick="showChangePasswordDiv()">修改</a>
                    </li>
                    <li class="w540">
                        <h1>详细信息</h1>
                        <label>
                            <span>真实姓名：</span>
                            <input class="w442" name="realName" id="real_name_div" value="${user.realName}"/>
                        </label>
                        <label>
                            <span>手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</span>
                            <p>${user.mobile}</p>
                            <a href="javascript:;" onclick="showChangMobilDiv()">修改</a>
                        </label>
                        <label>
                            <span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span>
                            <input class="w442" id="email" name="email" value="${user.email}"/>
                        </label>
                        <label>
                            <span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
                            <div class="radio" name="sex" onclick="setSex(0)" value="is_sex_0">
                                <ins class="checked"></ins>
                                <span class="color00">男</span></div>
                            <div class="radio" name="sex" onclick="setSex(1)" value="is_sex_1">
                                <span class="color00">女</span>
                            </div>

                        </label>
                        <label>
                            <span>公司名称：</span>
                            <input class="w442" name="companyName"/>

                        </label>
                        <label>
                            <span>职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位：</span>
                            <input class="w442" name="position" value="${user.position}"/>
                        </label>
                        <label>
                            <span>所在城市：</span>
                            <div class="com_select">

                                <select ms-duplex-string="province" id="province" name="province">
                                    <option value="-1">--请选择省/自治区--</option>
                                    <c:forEach items="${provinceList}" var="dt">
                                        <option value="${dt.provinceid}"
                                                c_id="${dt.provinceid}">${dt.provincename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="com_select">
                                <select ms-duplex-string="city" id="city" name="city" style="width: 130px;">
                                    <option value="-1">--请选择市--</option>
                                </select>
						<span style="display: none;" class="city_data">
							<c:forEach items="${cityList}" var="dt">
                                <option value="${dt.cityid}" c_id="${dt.cityid}" style=""
                                        top_city_id="${dt.provinceid}">${dt.cityname}
                                </option>
                            </c:forEach>
						</span>
                            </div>
                        </label>
                        <label>
                            <span class="ml_30">感兴趣的行业：</span>

                            <div class="com_select">

                                <select ms-duplex-string="fathercat" id="fathercat" name="fathercat">
                                    <option value="-1">--请选择第一行业--</option>
                                    <c:forEach items="${fatherCategoryList}" var="dt">
                                        <option value="${dt.catid}" c_id="${dt.catid}">${dt.categoryname}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="com_select">
                                <div id="soncat">
                                    <select ms-duplex-string="cat" id="secondcat" name="secondcat">
                                        <option value="-1">--请选择第二行业--</option>
                                    </select>
                                </div>
                                <span style="display: none;" class="cat_data">
                                    <c:forEach items="${categoryList}" var="dt">
                                        <option value="${dt.catid}" c_id="${dt.catid}" style=""
                                                top_cat_id="${dt.fathercatid}">${dt.categoryname}</option>
                                    </c:forEach>
                                </span>
                            </div>
                        </label>
                        <label>
                            <span class="ml_30 mt_13">感兴趣的标签：</span>

                            <div class="pro_tag user_info_tag">
                                <c:forEach items="${labelList}" var="x" varStatus="status">
                                    <c:if test="${status.count eq 1 || (status.count-1) % 5 eq 0}">
                                        <ul>
                                    </c:if>
                                    <li id="labels_check" class="" lang_id="${x.labelid }" lang_name="${x.labelname }"
                                        onclick="clickLabel(this, ${x.labelid });">${x.labelname }</li>
                                    <c:if test="${status.count % 5 eq 0 || status.count eq 5}">
                                        </ul>
                                    </c:if>
                                </c:forEach>
                            </div>

                        </label>
                        <label>
                            <span>个性签名：</span>
                            <input name="signature" value="${user.signature}" id="signature"
                                   onkeyup="signatureContent()" class="w442"/><span class="wrong" id="signature_tip">还可以输入<i>50</i>字符/汉字</span>
                        </label>
                    </li>
                </ul>
                <button type="submit" onclick=" return submitForm()">确&nbsp;&nbsp;定</button>
            </div>
            <div>

            </div>
        </form>
        <div style="clear: both;"></div>
    </div>
    <!-- /Container -->
    <!--修改密码-->
    <div class="bg_fixed_password" style="display: none;">
        <div class="edit_pass_box">
            <i class="close_box" onclick="cancelChangePassword()"></i>
            <h2>密码修改</h2>
            <input type="password" id="old_password" placeholder="请输入原始密码"/>
            <input type="password" id="new_password1" placeholder="设定密码"/>
            <input type="password" id="new_password2" placeholder="确认密码"/>

            <div class="bottom_btn">
                <h2 id="password_error"></h2>
                <a href="javascript:;"  onclick="cancelChangePassword()" class="cancel">取消</a>
                <a href="javascript:;"  onclick="changePassword()" class="save">保存</a>
            </div>
        </div>
    </div>

    <!--修改手机-->
    <div class="bg_fixed_phone" style="display: none">
        <div class="edit_phone_box">
            <i ID="update_mobile" class="close_box"></i>
           <span class="step1" style="display: none">
               <p>输入图片验证码</p>
               <label>
                   <input id="picture_code"/><img id="code_img" onclick="this.src='/user/getSecurityCode?t='+(new Date()).getTime()"/><a
                       onclick="refreshIcon()" class="refresh_icon"></a>
               </label>
               <span id ="error_tip_1"></span>
               <a class="btn" onclick="nextStep(1)">确定提交</a>
               <div style="clear: both"></div>
           </span>
           <span class="step2" style="display: none">
               <p>为了账号安全,炫耀验证手机有效性</br>
                   一条包含有验证的短信已发送至手机：</br>
                   <i>${user.mobile}</i>
               </p>
               <label>
                   <input id="old_checkCode"/><span id="span_old">60秒后重新发送</span><input style="display: none" id="btn_old" onclick="validatePicture()" type="button" value="短信">
               </label>
               <span id ="error_old_checkCode"></span>
               <a class="btn_l" onclick="cancelNext(2)">取消</a><a class="btn_r" onclick="nextStep(2)">下一步</a>
                <div style="clear: both"></div>
           </span>

            <span class="step3" style="display: none">
               <div class="step_con">
                   <span class="step_bar">
                       <p class="completed"></p>
                   </span>

                   <span class="step_name">
                       <p class="l">输入新手机</p>
                       <p class="c">验证新手机</p>
                       <p class="r">完成</p>
                   </span>
               </div>
               <label>
                   <input id="changed_mobile" type="text" placeholder="输入您的手机号"/>
               </label>
                   <span id ="error_tip_mobile"></span>
               <a class="btn_l" onclick="cancelNext(3)">取消</a><a class="btn_r" onclick="nextStep(3)">下一步</a>
                 <div style="clear: both"></div>
           </span>


            <span class="step4" style="display: none">
             <div class="step_con">
                   <span class="step_bar">
                       <p class="completed" style="width: 260px;"></p>
                   </span>

                   <span class="step_name">
                       <p class="l">输入新手机</p>
                       <p class="c">验证新手机</p>
                       <p class="r">完成</p>
                   </span>
             </div>
               <label>
                   <p>一条包含有验证码的短信已发送至手机：<i id="new_mobile"></i></p>
                   <input id="new_checkCode"/><span id="span_new">60秒后重新发送</span><input style="display: none" id="btn_new" onclick="getValidate('other')" type="button" value="短信">
               </label>
                   <span id ="error_new_checkCode"></span>
                 <span id ="error_tip_change_mobile"></span>
               <a class="btn_l" onclick="cancelNext(4)">取消</a><a class="btn_r" onclick="nextStep(4)">下一步</a>
                 <div style="clear: both"></div>
           </span>


           <span class="step5" style="display: none">
                <div class="step_con">
                   <span class="step_bar">
                       <p class="completed" style="width: 530px;"></p>
                   </span>

                   <span class="step_name">
                       <p class="l">输入新手机</p>
                       <p class="c">验证新手机</p>
                       <p class="r">完成</p>
                   </span>
                </div>
              <p class="success">恭喜您,已成功修改手机号!</p>
              <a class="succ_btn" onclick="nextStep(5)">完成</a>
                <div style="clear: both"></div>
           </span>


        </div>
    </div>

    <!--#include virtual="/html/footer/my_footer.html"-->
</div>
<!-- /Container -->


<!--右侧固定图标-->
<!--#include virtual="/html/right/right.html"-->
<!-- 置顶图标 -->
<ul id="jump">
    <li><a id="top" href="#top"></a></li>
</ul>
<!-- jQuery -->
<script type="text/javascript">
    var origSelOjbs;
    var stepFlag = 1;
    var  countdown = 60;
    var  countdown1 = 60;
    jQuery(function () {
        jQuery("#code_img").trigger("click");
       // initialUserStatus();
        //initialUserInfo();
        initial();
        jQuery("#update_mobile").click(function () {
            jQuery(".bg_fixed_phone").fadeOut("slow");
            jQuery(".edit_phone_box").fadeIn("slow");
            jQuery(".step" + stepFlag).hide();

        })
    });
    function submitForm() {
        var str = jQuery("#signature").val();
        var len = getStringLen(str);
        if (len > 50) return false;
        var realName = jQuery("#real_name_div").val();
        if( realName == ""){
            o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',"为了更好的称呼您，请务必填写您的真实姓名！");
            return false;
        }
        var email = jQuery("#email").val();
        if (email != "" && !isEmail(email)) {
            o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',"邮箱格式不正确");
            return false;
        }
        set_value_for_form();

        return true;
    }
    function signatureContent() {
        var str = jQuery("#signature").val();
        var length = getStringLen(str);
        if (length > 50) {
            jQuery("#signature_tip").html("已超过<i>" + (50 - length) + "</i>字");
        } else {
            jQuery("#signature_tip").html("还可以输入<i>" + (50 - length) + "</i>字符/汉字");
        }

    }
    function initial() {
        setTimeout(function () {
	        if ("${user.categoryId}" != null && "${user.categoryId}" != "" && "${user.categoryId}" != "-1") {
	            //一级行业
                var cat_obj = jQuery("#select_info_fathercat");
                cat_obj.attr("lang", "${user.categoryId}");
                cat_obj.text("${user.categoryName}");
                if("${user.secondCatIds}" != "-1") {
	                //二级行业
	                jQuery("#secondcat").remove();
	                var soncat_html = "<select ms-duplex-string='cat' id='secondcat' name='secondcat'></select>";
	                jQuery("#soncat").html(soncat_html);
	
	                jQuery('.cat_data option').each(function () {
	                    if (jQuery(this).attr("top_cat_id").trim() == "${user.categoryId}") {
	                        jQuery('[ms-duplex-string="cat"]').append(jQuery(this).clone());
	                    } else {
	                    }
	                });
	                jQuery("#select_secondcat").remove();
	                origSelOjbs = jQuery('#secondcat').selectlist();
	                jQuery.setMulSelectValues(origSelOjbs[0], "${user.secondCatIds}");
                }
	        }
            //省
            if ("${user.provinceId}" != null && "${user.provinceId}" != "") {
                var province_obj = jQuery("#select_info_province");
                province_obj.attr("lang", "${user.provinceId}");
                province_obj.text("${user.provinceName}");
            }
            //市
            if ("${user.cityId}" != null && "${user.cityId}" != "") {
            	clickOptions(5, 3, "province", "${user.provinceId}");
                var city_obj = jQuery("#select_info_city");
                city_obj.attr("lang", "${user.cityId}");
                city_obj.text("${user.cityName}");
            }
        }, 800);
        
        //标签
        jQuery("li#labels_check").each(function () {
            var value = "}${user.interestLabel}";
            if (value.indexOf("}" + jQuery(this).attr('lang_id') + "=") >= 0) {
                jQuery(this).attr("class", "active");
            }
        });
    }
    
    //没有用，防止报错
    function hide_error_span(){}
    
    function set_value_for_form() {

        //jQuery("#imgFile").removeAttr("name");//移除上传图片按钮的name属性
        if (jQuery("#select_info_fathercat").attr("lang") != -1) {
            jQuery("#cat_id").val(jQuery("#select_info_fathercat").attr("lang"));//一级行业id
            jQuery("#category_name").val(jQuery("#select_info_fathercat").text());//一级行业name
        } else {
            jQuery("#cat_id").val("");
            jQuery("#category_name").val("");
        }
        if (origSelOjbs && origSelOjbs.length > 0) {
            jQuery("#second_cat_id").val(jQuery.getMulSelectValues(origSelOjbs[0]));//二级行业id
            jQuery("#second_cat_name").val(jQuery("input.select-button").attr("title"));

        }

        // 获取选中的所有标签
        var labels = "";
        jQuery("li#labels_check").each(function () {
            var label_class = jQuery(this).attr("class");
            if (label_class == 'active') {
                labels = jQuery(this).attr("lang_id") + "=" + jQuery(this).attr("lang_name") + "{#}" + labels;
            }
        });
        jQuery("#labels").val(labels);
        if (jQuery("#select_info_province").attr("lang") != -1) {
            jQuery("#province_id").val(jQuery("#select_info_province").attr("lang"));//省
            jQuery("#province_name").val(jQuery("#select_info_province").html());
        } else {
            jQuery("#province_id").val("-1");
            jQuery("#province_name").val("请选择");
        }
        if (jQuery("#select_info_city").attr("lang") != -1) {
            var cityId = jQuery("#select_info_city").attr("lang");
            jQuery("#city_id").val(cityId);//市
            var cityName = jQuery("#select_info_city").html();
            jQuery("#city_name").val(cityName);

        } else {
            jQuery("#city_id").val("-1");
            jQuery("#city_name").val("请选择");
        }
    }
    function clickLabel(obj, id) {
        var classvalue = jQuery(obj).attr("class");
        if (classvalue == "active") {
            jQuery(obj).attr("class", "");
        }
        var i = 0;
        jQuery("li#labels_check").each(function () {
            var label_class = jQuery(this).attr("class");
            if (label_class == 'active') {
                i++;
            }
        });
        if (i >= 5) {
            o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',"最多选择5个标签");
            return false;
        }

        if (classvalue != "active") {
            jQuery(obj).attr("class", "active");
        }
    }

    /**
     * 选择上传图片，点击选择按钮触发
     */
    function imageChange() {
        if (jQuery("#imgFile").val()) {
            //异步表单提交 先提交图片
            var imgFileHidden = jQuery("#imgFileHidden").val();
            jQuery("#save_user_info").ajaxSubmit({
                type: "POST",
                url: "/demand/uploadTemporaryPic",
                dataType: "text",
                data: {
                    fileType: "imge"
                },
                success: function (data) {
                    jQuery("#user_icon").attr("src", "http://p1.binggou.com"+data+"?t="+(new Date()).getTime());
                    jQuery("#userImg").val(data);
                }
            });
        }
    }

    function setSex(id) {
        jQuery("#userSex").val(id);
    }
    function refreshIcon() {
        jQuery("#code_img").trigger("click");
    }
    function showChangMobilDiv() {
        jQuery(".bg_fixed_phone").show();
        if(stepFlag >1){
            jQuery(".step"+stepFlag).show();
        }else{
            jQuery(".step1").show();
        }

    }
    function cancelNext(index) {
        jQuery(".step" + (index + 1)).hide();
        jQuery(".bg_fixed_phone").hide();
    }
    function nextStep(index) {
        if(index == 1){
            validatePicture();
        }else if(index == 3){

            getNextStatus();
        }else if(index == 2){
            var checkCode = jQuery("#old_checkCode").val();
            if(checkCode == ""){
                jQuery("#error_old_checkCode").html("验证码不能为空！");
            }else{
                hideOrShowDiv(2);
            }

        }else if(index == 4){
            var newCheckCode = jQuery("#new_checkCode").val();
            if(newCheckCode == ""){
                jQuery("#error_new_checkCode").html("验证码不能为空！");
            }else{
                changeMobile();
            }
            //调用服务器进行修改手机号！
        }else{
            hideOrShowDiv(index);
        }



    }
    function hideOrShowDiv(index){
        jQuery(".step" + index).hide();
        if (index <= 4) {
            jQuery(".step" + (index + 1)).show();
            stepFlag = index + 1;
        } else {
            stepFlag =1;
            jQuery(".bg_fixed_phone").hide();
        }
    }
    function validatePicture(){
      var validateCode = jQuery("#picture_code").val();
      if(validateCode == ''){
          jQuery("#error_tip_1").html("验证码不能为空！")
          return false;
      }
      jQuery.ajax({
          url: "/user/mobile/"+validateCode,
          type: "GET",
          async:true,
          cache: false,
          success: function (data) {
              if(data.flag !='success'){
                  jQuery("#error_tip_1").html(data.msg);
              }else{
                  hideOrShowDiv(1);
                  showTimeOld();
              }

          }
      });
  }
    function getNextStatus(){
        var mobile = jQuery("#changed_mobile").val();
        if(!mobileValidate(mobile)){
            jQuery("#error_tip_mobile").html("手机号格式不正确！");
            return false;
        }
        jQuery('#new_mobile').html( mobile.substr(0,3)+"****"+mobile.substr(7,11));
        jQuery("#error_tip_mobile").html("");
        jQuery.ajax({
            url: "/user/sendCode",
            type: "GET",
            cache: false,
            data: {
                mobile: mobile,
                codeType: "other"
            },
            success: function (data) {
                if (data.success) {
                    showTimeNew();
                    hideOrShowDiv(3);
                } else {
                    jQuery("#error_tip_mobile").html(data.msg);
                }
            }
        });
    }
    function getValidate(type){
        jQuery("#btn_new").hide();
        var mobile = jQuery("#changed_mobile").val();
        if(!mobileValidate(mobile)){
            jQuery("#error_tip_mobile").html("手机号格式不正确！");
            return false;
        }
        jQuery.ajax({
            url: "/user/sendCode",
            type: "GET",
            cache: false,
            data: {
                mobile: mobile,
                codeType: type
            },
            success: function (data) {
                if (data.success) {
                    if(id=="new"){
                        showTimeNew();
                    }else{
                        showTimeOld();
                    }

                } else {
                    jQuery("#error_tip_mobile").html(data.msg);
                }
            }
        });
    }
    function showTimeOld() {
        if (countdown == 1) {
            jQuery("#span_old").html("");
            jQuery("#btn_old").show();
            countdown = 60;
        } else {
            jQuery("#span_old").html(countdown+"秒后重试！");
            jQuery("#btn_old").hide();
            countdown--;
            setTimeout(function () {
                showTimeOld()
            }, 1000)
        }
    }
    function showTimeNew() {
        if (countdown1 == 1) {
            jQuery("#span_new").html("");
            jQuery("#btn_new").show();
            countdown1 = 60;
        } else {
            jQuery("#span_new").html(countdown1+"秒后重试！");
            jQuery("#btn_new").hide();
            countdown1--;
            setTimeout(function () {
                showTimeNew()
            }, 1000)
        }
    }
    function mobileValidate(value) {
        var regMobile = /^(13[0-9]||15[012356789]||18[0123456789]||147||145||17[0-9])\d{8}$/;
        if (!regMobile.test(value)) {
            return false;
        }
        return true;
    }
    function changeMobile(){
        var picCheckCode = jQuery("#picture_code").val();
        var newMobile= jQuery("#changed_mobile").val();
        var oldCheckCode = jQuery("#old_checkCode").val();;
        var newCheckCode = jQuery("#new_checkCode").val();
        jQuery.ajax({
            url: "/user/change/mobile",
            type: "POST",
            cache: false,
            data: {
                picCheckCode: picCheckCode,
                oldCheckCode :oldCheckCode ,
                newCheckCode :newCheckCode ,
                mobile:newMobile
            },
            success: function (data) {
                if ("success" == data.flag) {
                    hideOrShowDiv(4);
                } else {
                    jQuery("#error_tip_change_mobile").html(data.msg);
                }
            }
        });
    }
    function showChangePasswordDiv(){
       jQuery(".bg_fixed_password").show();
       jQuery(".edit_pass_box").show();
    }
    function cancelChangePassword(){
        jQuery(".bg_fixed_password").hide();
        jQuery(".edit_pass_box").hide();
    }
    function changePassword(){
        var oldPwd = jQuery("#old_password").val();
        var newPwd1 = jQuery("#new_password1").val();
        var newPwd2 = jQuery("#new_password2").val();
        if(newPwd1 != newPwd2){
            showPassError("新密码不一致");
            return false;
        }
        if(checkPasswordOnBlur()){
            jQuery.ajax({
                url: "/user/change/password",
                type: "POST",
                cache: false,
                data: {
                    oldPassword: oldPwd,
                    newPassword :newPwd1

                },
                success: function (data) {
                    if ("success" == data.flag) {
                        cancelChangePassword();
                        o_confirm('bg_fixed_bg_noBtn','win_box_noBtn','win_close_noBtn',"修改密码成功！");
                        //showPassError("修改密码成功！");
                    } else {
                        showPassError(data.msg);
                    }
                }
            });
        }

    }

    function checkPasswordOnBlur() {
        var b = check_pwd();
        if (b == 1) {
            showPassError("密码不能为空");
            return false;
        } else {
            if (b == 2) {
                showPassError("密码为6-20位字符");
                return false;
            } else {
                if (b == 3) {
                    showPassError("密码为6-20位字符");
                    return false;
                } else {
                    if (b == 4) {
                        showPassError("密码中不允许有空格");
                        return false;
                    } else {
                        if (b == 5) {
                            showPassError("密码不能全为数字");
                            return false;
                        } else {
                            if (b == 6) {
                                showPassError("密码不能全为字母，请包含至少1个数字或符号 ");
                                return false;
                            } else {
                                if (b == 7) {
                                    showPassError("密码不能全为符号");
                                    return false;
                                } else {
                                    if (b == 8) {
                                        showPassError("密码不能全为相同字符或数字");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    var commonSymbol = "[\\,\\`\\~\\!\\@\\#\\$\\%\\\\^\\&\\*\\(\\)\\-\\_\\=\\+\\[\\{\\]\\}\\\\|\\;\\:\\‘\\’\\“\\”\\<\\>\\/?]+";
    function check_pwd() {
        var p =  jQuery("#new_password1").val();
        if (p == "") {
            return 1;
        }
        if (p.length > 20) {
            return 2;
        }
        if (p.length < 6) {
            return 3;
        }
        var m = /\s+/;
        if (m.test(p)) {
            return 4;
        }
        var k = /^[0-9]+$/;
        if (k.test(p)) {
            return 5;
        }
        var j = /^[a-zA-Z]+$/;
        if (j.test(p)) {
            return 6;
        }
        var o = /^[^0-9A-Za-z]+$/;
        if (o.test(p)) {
            return 7;
        }
        if (isSameWord(p)) {
            return 8;
        }
        var n = "d*" + commonSymbol + "";
        var i = "\\\d+[A-Za-z]|[A-Za-z]+[0-9]+|[A-Za-z]+" + commonSymbol
                + "[0-9]+|[A-Za-z]+[0-9]+" + commonSymbol + "|" + n + "";
        var l = new RegExp(i);
        if (!l.test(p)) {
            return 10;
        }
        return 0;
    }
    function isSameWord(g) {
        var e;
        if (g != null && g != "") {
            e = g.charCodeAt(0);
            e = "\\" + e.toString(8);
            var f = "[" + e + "]{" + (g.length) + "}";
            var h = new RegExp(f);
            return h.test(g);
        }
        return true;
    }
    function showPassError(desc){
    jQuery("#password_error").html(desc);
    }
</script>


</body>

</html>