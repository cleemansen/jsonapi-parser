package com.birbit.jsonapi;

import com.birbit.jsonapi.vo.Author;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import static com.birbit.jsonapi.TestUtil.ARTICLE_DESERIALIZER;
import static com.birbit.jsonapi.TestUtil.AUTHOR_DESERIALIZER;
import static com.birbit.jsonapi.TestUtil.COMMENT_DESERIALIZER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by clemens on 14/12/2016.
 */

public class AuthorTest {

    @Test
    public void viewobjectWithoutRelationshipAndResourceLinkAnnotation() throws Exception {
        // prepare
        String json = TestUtil.readTestData("author.json");
        Gson gson = TestUtil.createGson(AUTHOR_DESERIALIZER, ARTICLE_DESERIALIZER, COMMENT_DESERIALIZER);

        // execute
        JsonApiResponse<Author[]> response
                = gson.fromJson(json, new TypeToken<JsonApiResponse<Author[]>>(){}.getType());

        // verify
        Author[] authors = response.getData();
        assertThat(authors.length, is(1));
        assertThat(authors[0].getLastName(), is("Fry"));
    }

}
