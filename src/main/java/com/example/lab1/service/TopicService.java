package com.example.lab1.service;

import com.example.lab1.data.TopicRepository;
import com.example.lab1.data.UserRepository;
import com.example.lab1.domain.Topic;
import com.example.lab1.web.data.transfer.TopicAddPayload;
import com.example.lab1.web.data.transfer.TopicUpdatePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class TopicService {

    private final TopicRepository topicRepository;

    private final UserRepository userRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean create(TopicAddPayload payload) {
        var optCreator = userRepository.findByUsername(payload.getCreatorUsername());
        if (optCreator.isEmpty()) {
            log.warn("Creator by username {} doesn't exists", payload.getCreatorUsername());
            return false;
        }
        var topic = new Topic();
        topic.setCreator(optCreator.get());
        topic.setTitle(payload.getTitle().strip());
        topic.setDescription(payload.getDescription().strip());
        topic.setCreator(optCreator.get());
        topicRepository.save(topic);
        return true;
    }

    @Transactional
    public boolean update(TopicUpdatePayload payload, long id) {
        var optTopic = topicRepository.findById(id);
        if (optTopic.isPresent()) {
            var topic = optTopic.get();
            if (payload.getTitle() != null) {
                topic.setTitle(payload.getTitle().strip());
            }
            if (payload.getDescription() != null) {
                topic.setDescription(payload.getDescription().strip());
            }
            topicRepository.save(topic);
            return true;
        }
        log.warn("Topic by id {} doesn't exists", id);
        return false;
    }

    @Transactional
    public Optional<Topic> findById(Long id) {
        return topicRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        if (topicRepository.existsById(id))
            topicRepository.deleteById(id);
    }

    @Transactional
    public Page<Topic> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(sortBy).descending());
        return topicRepository.findAll(pageable);
    }
}
