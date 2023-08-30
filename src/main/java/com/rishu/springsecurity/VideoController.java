package com.rishu.springsecurity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoController {

   private final VideoService videoService;

    public VideoController(VideoService videoService) {
         this.videoService = videoService;
    }

    @GetMapping("/videos")
    public List<VideoEntity> getVideos() {
        return videoService.getVideos();
    }

    @GetMapping("/videos/{id}")
    public VideoEntity getVideo(@PathVariable Long id) {
        return videoService.getVideo(id);
    }

    @PostMapping("/videos")
    public VideoEntity create(@RequestBody NewVideo newVideo) {
        return videoService.create(newVideo);
    }

    @DeleteMapping("/videos/{id}")
    public VideoEntity delete(@PathVariable Long id) {
        return videoService.delete(id);
    }

    @PutMapping("/videos/{id}")
    public VideoEntity update(@PathVariable Long id, @RequestBody NewVideo newVideo) {
        return videoService.update(id, newVideo);
    }

    @GetMapping("/videos/search")
    public List<VideoEntity> search(@RequestParam String query) {
        return videoService.search(new UniversalSearch(query));
    }

}
