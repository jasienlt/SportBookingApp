<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<%
    // Simulated club data retrieval based on clubId parameter
    String clubId = request.getParameter("clubId");
    String clubName = "Default Club Name";
    String address = "Default Address";
    String details = "Default details about the club.";
    String contact = "Default contact info.";

    if ("1".equals(clubId)) {
        clubName = "CLB Cầu Lông 18B Cộng Hòa";
        address = "18B Cộng Hòa, Phường 4, Tân Bình, TP Hồ Chí Minh";
        details = "Detailed information about CLB Cầu Lông 18B Cộng Hòa.";
        contact = "0888278338";
    } else if ("2".equals(clubId)) {
        clubName = "272 Badminton";
        address = "Lô A4 Chung Cư Ngô Quyền, TP Đà Lạt, Lâm Đồng";
        details = "Detailed information about 272 Badminton.";
        contact = "0888278339";
    }
    // Add more conditions for other clubs

%>
<div class="card-body">

    <div class="headerImg">
        <img class="courtImg" src="resource/image/homepage_big.png" />
        <div class="d-flex flex-row justify-content-between align-items-center">
            <img class="logoImg" src="resource/image/logo.png" />
            <div class="rating grid-container">
                <strong class="courtName">Badminton VT</strong>
                <div class="ratingStar">
                    <span>0</span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                </div>
            </div>
            <div>
                <a class="ms-auto" href="./booking">
                    <button
                            class="btn btn-primary mx-1 my-3 align-self-center text-white bookingPageBtn"
                            type="button"
                    >
                        <spring:message key="booking"/>
                    </button>
                </a>
            </div>
        </div>
        <p><strong>Thông tin:</strong> <%= details %></p>
        <p><i class="bi bi-geo-alt"></i> <%= address %></p>
        <p><i class="bi bi-telephone"></i> <%= contact %></p>
    </div>
</div>
