package gh.sample.springboot.Controller;

import gh.sample.springboot.Domain.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//RestController 어노테이션 추가 (controller 인식)
@RestController
// value : localhost:8080/posts로 접근할 경우 이 controller에 접근한다.
// produces : 생산 가능한 미디어 타입을 지정해서 일치할 때만 요청을 매칭함. (매핑 제한용)
// comsumes : 소비 가능한 미디어 타입을 지정해서 때만 요청을 매칭함. (매핑 제한용)
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    //게시글 추가
    //Post 요청이 들어왔을 때, value=""는 localhost:8080/posts를 의미함
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    //ResponseEntity 상태코드 제어
    public ResponseEntity<Void> createPost(@RequestBody Map<String, Object> requestBody){
        //Postman으로 요청했을 때 딱히 뜨는 메시지가 없어서 확인차 로그를 찍어봄
        System.out.println("createPost");
        //정상적으로 수행됐다고 상태 리턴 (200)
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //게시글 목록 조회
    //Get 요청이 들어왔을 때, value=""는 localhost:8080/posts를 의미함
    @GetMapping(value = "")
    //RequestParam : 넘어온 파라미터 가지고 올때 사용 required = false를 사용하면 필수값 아님을 의미
    public List<Post> getPostList(@RequestParam(value = "postId", required = false) String postId){
        //게시글 데이터가 조회가 되려면 데이터가 있어야 하는데 없으므로 임의로 10개만 생성해 줌
        //Post 객체로 ArrayList를 생성
        ArrayList<Post> posts= new ArrayList<>();
        for(int i=1; i <= 10; i++){
            //Post 객체를 생성해서 데이터를 title과 contents에 postId만 붙여서 넣어줌
            Post post = new Post();
            post.setPostId(Integer.toString(i));
            post.setTitle("title" + i);
            post.setContents("content" + i);
            //ArraryList에 Post 객체를 넣어줌
            posts.add(post);
        }

        return posts;
    }

    //특정 게시글 조회
    //Get 요청이 들어왔을 때, value = "/{postId}"는 postId 게시글의 데이터를 조회하기 위해 설정
    //ex) localhost:8080/posts/1
    @GetMapping(value = "/{postId}")
    //PathVariable은 URI에 넘어온 postId 값을 가져오기 위해 사용
    public Post getPost(@PathVariable String postId){
        // 데이터베이스 연동이 따로 되어있는 것이 없기 때문에 임시적으로 객체를 생성해서 넘겨줌
        return new Post(postId,"title"+postId,"contents"+postId);
    }

    //Put 요청이 들어왔을 때, value = "/{postId}"는 postId 게시글의 데이터를 수정하기 위해 설정
    @PutMapping(value = "/{postId}")
    public ResponseEntity<Void> updatePost(
            //PathVariable은 URI에 넘어온 postId 값을 가져오기 위해 사용
            @PathVariable String postId
    ){
        //Postman으로 요청했을 때 딱히 뜨는 메시지가 없어서 확인차 로그를 찍어봄
        System.out.println("UpdatePost");
        //이 또한 데이터베이스가 연동되어 있지 않기 때문에 임시로 처리
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete 요청이 들어왔을 때, value = "/{postId}"는 postId 게시글의 데이터를 삭제하기 위해 설정
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable String postId
    ){
        //Postman으로 요청했을 때 딱히 뜨는 메시지가 없어서 확인차 로그를 찍어봄
        System.out.println("DeletePost");
        //이 또한 데이터베이스가 연동되어 있지 않기 때문에 임시로 처리
        return new ResponseEntity<>(HttpStatus.OK);
    }
}