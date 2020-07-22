package com.leiquant.core.config.convert

import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.stereotype.Component

@Component
class CustomMappingJackson2HttpMessageConverter: MappingJackson2HttpMessageConverter {
  constructor() {
    val mediaTypes: MutableList<MediaType> = mutableListOf()
    mediaTypes.add(MediaType.TEXT_PLAIN);
    mediaTypes.add(MediaType.TEXT_HTML);  //加入text/html类型的支持

    supportedMediaTypes = mediaTypes;// tag6
  }
}