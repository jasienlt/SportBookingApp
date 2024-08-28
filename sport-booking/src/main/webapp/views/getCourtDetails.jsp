<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card-body">
    <div class="headerImg">
        <img class="courtImg" src="resource/image/homepage_big.png" />
        <div class="d-flex flex-row justify-content-between align-items-center">
            <img class="logoImg" src="resource/image/logo.png" />
            <div class="rating grid-container">
                <strong class="courtName">${court.name}</strong>
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
                <a class="ms-auto" href="./booking?courtId=${court.id}">
                    <button
                            class="btn btn-primary mx-1 my-3 align-self-center text-white bookingPageBtn"
                            type="button"
                    >
                        <spring:message key="booking"/>
                    </button>
                </a>
            </div>
        </div>
        <p><strong>Thông tin:</strong> ${court.name} </p>
        <p><i class="bi bi-geo-alt"></i> ${court.address}</p>
        <p><i class="bi bi-telephone"></i> ${court.phone}</p>
        <div id="map"></div>
    </div>
</div>

<%--<script>--%>
<%--    let map;--%>

<%--    async function initMap() {--%>
<%--        const { Map } = await google.maps.importLibrary("maps");--%>

<%--        map = new Map(document.getElementById("map"), {--%>
<%--            center: { lat: -34.397, lng: 150.644 },--%>
<%--            zoom: 8,--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>

<%--<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCKURs_tLLjghrsjLMcoh-xqtaA2KDmlQ&loading=async&callback=initMap" defer></script>--%>


