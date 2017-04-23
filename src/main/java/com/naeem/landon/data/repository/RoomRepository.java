package com.naeem.landon.data.repository;

import com.naeem.landon.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by lenovo on 4/23/2017.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room,Long>{
    Room findByNumber(String number);

}
