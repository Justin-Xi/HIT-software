package com.example.hitsoftware.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这个类是属于Courier的独有类，只有用户是Courier时，
 * 前端才应调用其中的方法。
 */
@RestController
@Slf4j
@RequestMapping("/courier")
public class CourierController {
}
