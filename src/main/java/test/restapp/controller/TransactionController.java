package test.restapp.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.restapp.entity.Transaction;
import test.restapp.entity.User;
import test.restapp.exception.InvalidValueException;
import test.restapp.exception.NotFoundException;
import test.restapp.repository.TransactionRepository;
import test.restapp.repository.UserRepository;

import test.restapp.utils.DateTimeParser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionRepository tranRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return tranRepository.findAll();
    }

    @GetMapping("/transactions/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Integer userId) {
        return tranRepository.findByUserId(userId);
    }

    @GetMapping("/transactions/between/{startDate}/{endDate}")
    public List<Transaction> getTransactionsBetweenDates(@PathVariable String startDate,
                                                    @PathVariable String endDate) {
        Date dt1 = DateTimeParser.strToDate(startDate);
        Date dt2 = DateTimeParser.strToDate(endDate);
        if (dt1 == null) throw new InvalidValueException("Format for transaction date", DateTimeParser.dateTimeFormat);
        if (dt2 == null) throw new InvalidValueException("Format for transaction date", DateTimeParser.dateTimeFormat);

        return tranRepository.findByCreatedAtBetween(dt1, dt2);
    }

    @GetMapping("/transactions/from/{startDate}")
    public List<Transaction> getTransactionsFromDate(@PathVariable String startDate) {
        Date dt1 = DateTimeParser.strToDate(startDate);
        if (dt1 == null) throw new InvalidValueException("Format for transaction date", DateTimeParser.dateTimeFormat);
        return tranRepository.findByCreatedAtAfter(dt1);
    }

    @Transactional
    @PostMapping("/transactions/user/{userId}")
    public Transaction createTransaction(@PathVariable Integer userId, @RequestBody Transaction tran) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found (ID="+userId+")");
        }
        if (tran.getAmount() == null) {
            throw new InvalidValueException("Required value", "amount");
        }
        tran.setUserId(userId);
        Transaction retTran = tranRepository.save(tran);
        User user = optUser.get();
        Long bal = user.getBalance();
        user.setBalance(bal+tran.getAmount());
        userRepository.save(user);
        return retTran;
    }

}
