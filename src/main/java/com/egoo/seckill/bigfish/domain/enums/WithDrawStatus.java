package com.egoo.seckill.bigfish.domain.enums;

public enum WithDrawStatus {
    /**
     * INIT:未处理
     * FAILURE：失败
     * PROCESS：处理中
     * FINISH：完成
     * SUCCESS：成功
     */
    INIT(5),
    FAILURE(10),
    PROCESS(15),
    FINISH(20),
    SUCCESS(25);

    private final int code;

    WithDrawStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}