package com.birbit.jsonapi;

import com.birbit.jsonapi.vo.Author;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.birbit.jsonapi.TestUtil.ARTICLE_DESERIALIZER;
import static com.birbit.jsonapi.TestUtil.AUTHOR_DESERIALIZER;
import static com.birbit.jsonapi.TestUtil.COMMENT_DESERIALIZER;
import static org.assertj.core.api.Assertions.*;

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
        assertThat(authors.length).isEqualTo(1);
        assertThat(authors[0].getLastName()).isEqualTo("Fry");
        assertThat((List<String>)response.getMeta().get("meta_key_0")).containsExactly("a0", "a1");
        assertThat(((Map<String, Object>)response.getMeta().get("meta_key_1")).get("key")).isEqualTo("value");
        assertThat(((Map<String, Object>)response.getMeta().get("meta_key_1")).get("number")).isEqualTo(58.0);
    }

}
