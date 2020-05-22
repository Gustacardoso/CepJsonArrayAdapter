package com.gcsapps.exemplosretrofit.api;

import com.gcsapps.exemplosretrofit.model.CEP;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("RS/{cep}/{end}/json")
    Call<List<CEP>> recuperarCEP(@Path("cep") String cep, @Path("end") String end);
}
