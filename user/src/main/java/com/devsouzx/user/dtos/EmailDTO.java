package com.devsouzx.user.dtos;

import java.util.UUID;

public record EmailDTO(UUID userId, String to, String subject, String text) {
}
