
CREATE TABLE profiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE courses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_user_profile_id FOREIGN KEY (profile_id) REFERENCES profiles(id)
);


CREATE TABLE topics (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    message VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT NOW(),
    status VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_topic_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_topic_course_id FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE responses (
    id BIGINT NOT NULL AUTO_INCREMENT,
    message VARCHAR(255) NOT NULL,
    createdAt TIMESTAMP DEFAULT NOW(),
    user_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    solution VARCHAR(255) NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_response_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);


