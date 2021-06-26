package com.example.restaurant.wishList.repository;

import com.example.restaurant.db.WishListRepository;
import com.example.restaurant.wishList.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create(){
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("road address");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }
    @Test
    public void saveTest(){

        var wishList = create();
        var expected = wishListRepository.save(wishList);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1,expected.getIndex());


    }

    @Test
    public void updateTest(){

        var wishList = create();
        var expected = wishListRepository.save(wishList);

        expected.setTitle("update Test");
        var saveEntity = wishListRepository.save(expected);

        Assertions.assertEquals("update Test", saveEntity.getTitle());
        Assertions.assertEquals(1, wishListRepository.listAll().size());


    }
    @Test
    public void findByIdTest(){
        var wishList = create();
        wishListRepository.save(wishList);

        var expected = wishListRepository.findById(1);

        Assertions.assertEquals(true, expected.isPresent());
        Assertions.assertEquals(1, expected.get().getIndex());

    }

    @Test
    public void deleteTest(){
        var wishList = create();
        wishListRepository.save(wishList);

        wishListRepository.deleteById(1);

        int count = wishListRepository.listAll().size();

        Assertions.assertEquals(0,count);


    }

    @Test
    public void listAllTest(){
        var wishList1 = create();
        wishListRepository.save(wishList1);

        var wishList2 = create();
        wishListRepository.save(wishList2);

        int count = wishListRepository.listAll().size();

        Assertions.assertEquals(2,count);


    }
}
