package com.egoo.seckill.bigfish.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsonViewResult {

    public interface app extends CommonView{}

    public interface web extends CommonView{}
}
