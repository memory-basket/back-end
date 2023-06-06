package com.flower.member.dto.response;

import com.flower.member.domain.Member;
import lombok.Getter;

@Getter
public class ConnectionLinkResponse {

    private String link;

    private ConnectionLinkResponse() {
    }

    private ConnectionLinkResponse(final String link) {
        this.link = link;
    }

    public static ConnectionLinkResponse of(final Member member) {
        return new ConnectionLinkResponse(member.getConnectionUrl());
    }
}
