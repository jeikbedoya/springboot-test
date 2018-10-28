package com.jeikode.test.tdd.product;

import com.jeikode.test.util.IntegrationTestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProduct_returnProductDetails() throws Exception {
        given(productService.getProductById(1L)).willReturn(new Product(1L,"test",100_000L));

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("test"))
                .andExpect(jsonPath("price").value(100_000))
                .andExpect(jsonPath("id").value(1));
    }

    @Test
    public void getProduct_returnNotFound() throws Exception {
        given(productService.getProductById(anyLong())).willThrow(new ProductNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createProduct_returnNewProduct() throws Exception {

        Product product = new Product(1L, "test", 100_000L);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.parseToJson(product))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateProduct_returnProductUpdated() throws Exception {
        Product product = new Product(1L, "test", 100_000L);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(IntegrationTestUtil.parseToJson(product))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
