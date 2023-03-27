package com.prokopovich.bankclient.domain.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSettings {

    private int threadCount;

    private int readQuota;

    private int writeQuota;

    private List<Long> readIdList;

    private List<Long> writeIdList;
}
