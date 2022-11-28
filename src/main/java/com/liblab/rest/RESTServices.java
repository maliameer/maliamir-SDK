package com.liblab.rest;

import retrofit2.Call;

import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.GET;

import com.liblab.api.model.Books;
import com.liblab.api.model.Chapters;
import com.liblab.api.model.Quotes;
import com.liblab.api.model.Characters;
import com.liblab.api.model.Movies;

public interface RESTServices {

   public static final String ID = "id";
   public static final String SORT = "sort";
   public static final String ASC = "asc";
   public static final String DESC = "desc";
   
   public static final String BOOK = "book";
   public static final String CHAPTER = "chapter";
   public static final String QUOTE = "quote";
   public static final String CHARACTER = "character";
   public static final String MOVIE = "movie";
   
   @GET(BOOK)
   Call<Books> getBooks();
   
   @GET("book/{id}")
   Call<Books> getBook(@Path(ID) String id);

   @GET(CHAPTER)
   Call<Chapters> getChapters(@Header(RESTCommunication.AUTHORIZATION) String authorization);
   
   @GET("chapter/{id}")
   Call<Chapters> getChapter(@Header(RESTCommunication.AUTHORIZATION) String authorization, @Path(ID) String id);

   @GET(QUOTE)
   Call<Quotes> getQuotes(@Header(RESTCommunication.AUTHORIZATION) String authorization);
   
   @GET("quote/{id}")
   Call<Quotes> getQuote(@Header(RESTCommunication.AUTHORIZATION) String authorization, @Path(ID) String id);

   @GET(CHARACTER)
   Call<Characters> getCharacters(@Header(RESTCommunication.AUTHORIZATION) String authorization);
   
   @GET("character/{id}")
   Call<Characters> getCharacter(@Header(RESTCommunication.AUTHORIZATION) String authorization, @Path(ID) String id);

   @GET(MOVIE)
   Call<Movies> getMovies(@Header(RESTCommunication.AUTHORIZATION) String authorization);
   
   @GET("movie/{id}")
   Call<Movies> getMovie(@Header(RESTCommunication.AUTHORIZATION) String authorization, @Path(ID) String id);

   @GET("/movie/{id}/quote")
   Call<Quotes> getMovieQuotes(@Header(RESTCommunication.AUTHORIZATION) String authorization, @Path(ID) String id);

}