import com.myApp.gamestore.GameStoreApplication;
import com.myApp.gamestore.entity.Library;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description:
 * @author:25445
 * @date:2022/11/30 18:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameStoreApplication.class)
public class test1 {

    @Autowired
    private LibraryService libraryService;

    @Test
    public void testAddUser() {
        List<Library> list=libraryService.getLibrary("hood1235");
        list.forEach(System.out::println);
    }

}
