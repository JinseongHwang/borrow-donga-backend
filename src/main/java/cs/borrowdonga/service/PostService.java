package cs.borrowdonga.service;

import cs.borrowdonga.domain.member.Member;
import cs.borrowdonga.domain.member.MemberRepository;
import cs.borrowdonga.domain.post.Post;
import cs.borrowdonga.domain.post.PostRepository;
import cs.borrowdonga.dto.post.PostCreateRequestDto;
import cs.borrowdonga.dto.post.PostResponseDto;
import cs.borrowdonga.dto.post.PostUpdateRequestDto;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /**
     * 게시글 등록(생성)
     */
    @Transactional
    public Long create(PostCreateRequestDto requestDto) {
        String studentNumber = requestDto.getStudentNumber();
        Member member = memberRepository.findMemberByStudentNumber(studentNumber);
        Post post = requestDto.toEntity(member);
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    /**
     * 게시글 조회
     */
    public PostResponseDto read(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return new PostResponseDto(optionalPost.orElseThrow(IllegalArgumentException::new));
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(Long postId, PostUpdateRequestDto requestDto) {
        Post originPost = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return originPost.update(requestDto);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        postRepository.delete(optionalPost.orElseThrow(IllegalArgumentException::new));
    }
}