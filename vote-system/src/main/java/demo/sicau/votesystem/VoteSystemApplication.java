package demo.sicau.votesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("demo.sicau.votesystem.dao") //扫描mybatis的mapperx
@ServletComponentScan
public class VoteSystemApplication {


    public static void main(String[] args) {
        SpringApplication.run(VoteSystemApplication.class, args);
    }
}
