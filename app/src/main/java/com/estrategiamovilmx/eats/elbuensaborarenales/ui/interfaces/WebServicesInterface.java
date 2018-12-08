package com.estrategiamovilmx.eats.elbuensaborarenales.ui.interfaces;



import com.estrategiamovilmx.eats.elbuensaborarenales.requests.AddProductRequest;
import com.estrategiamovilmx.eats.elbuensaborarenales.requests.ChangeStatusOrderRequest;
import com.estrategiamovilmx.eats.elbuensaborarenales.requests.CreateOrderRequest;
import com.estrategiamovilmx.eats.elbuensaborarenales.requests.RegisterDeviceRequest;
import com.estrategiamovilmx.eats.elbuensaborarenales.requests.UpdateShoppingCartRequest;
import com.estrategiamovilmx.eats.elbuensaborarenales.requests.UserOperationRequest;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.CategoryResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.ConfigurationResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.CreateOrderResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GenericResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GetCartResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GetContactsResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GetOrdersResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GetPaymentMethodResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GetProductsResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GetShippingAddressResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.GetVariantAdditionalResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.OrderDetailResponse;
import com.estrategiamovilmx.eats.elbuensaborarenales.responses.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by administrator on 19/07/2017.
 */
public interface WebServicesInterface {
    @GET("getProducts/{start}/{end}")
    Call<GetProductsResponse> getAllProducts(
            @Path("start") String start, @Path("end") String end);

    @GET("getLocationsByUser/{id_user}")
    Call<GetShippingAddressResponse> getLocationsByUser(
            @Path("id_user") String id_user);

    @GET("getContactsByUser/{id_user}")
    Call<GetContactsResponse> getContactsByUser(
            @Path("id_user") String id_user);

    @GET("getPaymentMethods")
    Call<GetPaymentMethodResponse> getPaymentMethods();

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("cartOperation")
    Call<GenericResponse> shoppingCart(@Body AddProductRequest cart);

    @GET("getShoppingCart/{id_user}")
    Call<GetCartResponse> getShoppingCart(
            @Path("id_user") String id_user);

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("createOrder")
    Call<CreateOrderResponse> createOrder(@Body CreateOrderRequest order);

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("updateShoppingCart")
    Call<GenericResponse> updateShoppingCart(@Body UpdateShoppingCartRequest request);

    @GET("getOrders/{id_user}/{id_profile}/{start}/{end}/{days}")
    Call<GetOrdersResponse> getOrders(
            @Path("id_user") String id_user, @Path("id_profile") String id_profile, @Path("start") int start, @Path("end") int end, @Path("days") String days);

    @GET("getDeliverManOrders/{id_user}/{id_profile}/{type_query}/{start}/{end}/{days}")
    Call<GetOrdersResponse> getDeliverManOrders(
            @Path("id_user") String id_user, @Path("id_profile") String id_profile, @Path("type_query") String type_query, @Path("start") int start, @Path("end") int end, @Path("days") String days);

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("userOperation")
    Call<UserResponse> userOperation(@Body UserOperationRequest request);

    @GET("getConfiguration")
    Call<ConfigurationResponse> getConfiguration();

    @GET("recoveryPassword/{email}/{newPassword}/{encrypted}")
    Call<GenericResponse> recoveryPassword(
            @Path("email") String email, @Path("newPassword") String newPassword, @Path("encrypted") String encrypted);

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("changeStatusOrder")
    Call<GenericResponse> ChangeStatusOrder(@Body ChangeStatusOrderRequest request);

    @Headers({"Content-Type: application/json", "Cache-Control: max-age=640000"})
    @POST("registerDevice")
    Call<GenericResponse> registerDevice(@Body RegisterDeviceRequest request);

    @GET("getDetailOrder/{id_order}")
    Call<OrderDetailResponse> getDetailOrder(@Path("id_order") String id_order);

    @GET("getCategories")
    Call<CategoryResponse> getCategories();

    @GET("getVariantsAditionals/{id_product}")
    Call<GetVariantAdditionalResponse> getVariantsXProduct(@Path("id_product") String id_product);
}
