package com.apple.shop;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

// 2. 등록하고
@Service
@RequiredArgsConstructor
public class ItemService {
    // 1. repository 만들고
    private final ItemRepository itemRepository;

    // DB 저장
    public void saveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    // List 나열
    public List<Item> listItem(){
       return itemRepository.findAll();
    }

    // 해당 id 객체 찾기
    public Optional<Item> findObjectId(long id){
        return itemRepository.findById(id);
    }

}
