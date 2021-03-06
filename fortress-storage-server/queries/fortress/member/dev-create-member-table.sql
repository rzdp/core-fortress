USE FortressDev;

CREATE TABLE Member (
	MemberId BIGINT IDENTITY (1,1) PRIMARY KEY NOT NULL,
	BankId VARCHAR(10) NOT NULL, 
	FirstName VARCHAR(50) NOT NULL,
	MiddleName VARCHAR(50) NULL,
	LastName VARCHAR(50) NOT NULL,
	BirthDate DATE NOT NULL,
	Active BIT NOT NULL DEFAULT 0,
	CreatedBy VARCHAR(50) NOT NULL,
	CreatedDate DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
	ModifiedBy VARCHAR(50) NOT NULL,
	ModifiedDate DATETIME2 NOT NULL DEFAULT SYSUTCDATETIME(),
)

 
INSERT INTO Member 
VALUES ('DE00000001', 'DEVELOPMENT', 'DEVELOPMENT', 'DEVELOPMENT', '04-16-1997', 1, 'SYSTEM', SYSUTCDATETIME(), 'SYSTEM', SYSUTCDATETIME());