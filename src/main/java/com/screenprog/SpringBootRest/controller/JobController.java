package com.screenprog.SpringBootRest.controller;

import com.screenprog.SpringBootRest.model.JobPost;
import com.screenprog.SpringBootRest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("jobPosts")
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @PostMapping("jobPost")
    public void addJob(@RequestBody JobPost job){
        service.addJob(job);
        System.out.println(job);
    }

    @GetMapping("jobPosts/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){
        System.out.println(service.getJob(postId));;
        return service.getJob(postId);
    }

    @GetMapping("jobPosts/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.searchByKeyword(keyword);
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int postId){
        return service.deleteJob(postId);
    }

    @GetMapping("jobPosts/load")
    public String load(){
        return service.load();
    }
}
