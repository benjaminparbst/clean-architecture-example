package com.netcompany.guestlecture.softwaremaintenance.usecase;

import java.util.Optional;

public interface UseCase <Input, Output> {
    Optional<Output> process(Input input);
}
