package com.monari.monariback.batch;

import com.monari.monariback.batch.scheduler.LessonFeeJobScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final LessonFeeJobScheduler lessonFeeJobScheduler;

    //테스트용
    @GetMapping("/test")
    public void test() throws Exception {
        lessonFeeJobScheduler.launchLessonFeeJob();
    }
}
