package com.rishu.springsecurity;


import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class VideoService {

    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<VideoEntity> getVideos() {
        return repository.findAll();
    }

    public VideoEntity getVideo(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public VideoEntity create(NewVideo newVideo) {
        return repository.saveAndFlush(new VideoEntity(newVideo.name(), newVideo.description()));
    }

    public VideoEntity delete(Long id) {
        VideoEntity video = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return video;
    }

    public VideoEntity update(Long id, NewVideo newVideo) {
        VideoEntity video = repository.findById(id).orElseThrow();
        video.setName(newVideo.name());
        video.setDescription(newVideo.description());
        repository.saveAndFlush(video);
        return video;
    }

    public List<VideoEntity> search(VideoSearch videoSearch) {
        if (StringUtils.hasText(videoSearch.name()) //
                && StringUtils.hasText(videoSearch.description())) {
            return repository //
                    .findByNameContainsOrDescriptionContainsAllIgnoreCase( //
                            videoSearch.name(), videoSearch.description());
        }

        if (StringUtils.hasText(videoSearch.name())) {
            return repository.findByNameContainsIgnoreCase(videoSearch.name());
        }

        if (StringUtils.hasText(videoSearch.description())) {
            return repository.findByDescriptionContainsIgnoreCase(videoSearch.description());
        }

        return Collections.emptyList();
    }

    public List<VideoEntity> search(UniversalSearch search) {
        VideoEntity probe = new VideoEntity();
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }
        Example<VideoEntity> example = Example.of(probe, //
                ExampleMatcher.matchingAny() //
                        .withIgnoreCase() //
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return repository.findAll(example);
    }

//    @PostConstruct
//    void initDatabase() {
//        repository.save(new VideoEntity("Need HELP with your SPRING BOOT 3 App?",
//                "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."));
//        repository.save(new VideoEntity("Don't do THIS to your own CODE!",
//                "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"));
//        repository.save(new VideoEntity("SECRETS to fix BROKEN CODE!",
//                "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."));
//    }
}