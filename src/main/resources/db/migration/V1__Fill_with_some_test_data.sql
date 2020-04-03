--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

-- Started on 2020-04-01 20:47:42

DROP TABLE public.flyway_schema_history;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 212 (class 1259 OID 22253)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
                                id bigint NOT NULL,
                                created_date timestamp without time zone,
                                last_modified_date timestamp without time zone,
                                version integer,
                                initial_amount numeric(19,2),
                                premium_fee numeric(19,2),
                                standard_fee numeric(19,2),
                                account_number character varying(255),
                                account_type character varying(255),
                                amount numeric(19,2),
                                fee numeric(19,2),
                                users bigint
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 22245)
-- Name: account_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_sequence OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 22179)
-- Name: auction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auction (
                                id bigint NOT NULL,
                                active boolean NOT NULL,
                                auction_owner_account_id bigint,
                                auction_owner_id bigint,
                                description character varying(255),
                                end_date timestamp without time zone,
                                price numeric(19,2),
                                quantity bigint,
                                start_date timestamp without time zone,
                                title character varying(255)
);


ALTER TABLE public.auction OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 22185)
-- Name: auction_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auction_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auction_sequence OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 22261)
-- Name: card; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.card (
                             id bigint NOT NULL,
                             created_date timestamp without time zone,
                             last_modified_date timestamp without time zone,
                             version integer,
                             card_number character varying(255),
                             cvv character varying(255),
                             expiration_date date,
                             account_id bigint
);


ALTER TABLE public.card OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 22247)
-- Name: card_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.card_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.card_sequence OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 22195)
-- Name: cards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cards (
                              id bigint NOT NULL,
                              created_date timestamp without time zone,
                              last_modified_date timestamp without time zone,
                              version integer,
                              card_number character varying(255),
                              cvv character varying(255),
                              expiration_date date,
                              account_id bigint
);


ALTER TABLE public.cards OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 22161)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
                                              installed_rank integer NOT NULL,
                                              version character varying(50),
                                              description character varying(200) NOT NULL,
                                              type character varying(20) NOT NULL,
                                              script character varying(1000) NOT NULL,
                                              checksum integer,
                                              installed_by character varying(100) NOT NULL,
                                              installed_on timestamp without time zone DEFAULT now() NOT NULL,
                                              execution_time integer NOT NULL,
                                              success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 22201)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 22203)
-- Name: order_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.order_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.order_sequence OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 22269)
-- Name: transfer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transfer (
                                 id bigint NOT NULL,
                                 created_date timestamp without time zone,
                                 last_modified_date timestamp without time zone,
                                 version integer,
                                 amount numeric(19,2),
                                 auction_owner_account_id bigint,
                                 auction_owner_id bigint,
                                 auction_winner_account_number character varying(255),
                                 title character varying(255)
);


ALTER TABLE public.transfer OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 22249)
-- Name: transfer_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transfer_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transfer_sequence OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 22251)
-- Name: user_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_sequence
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_sequence OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 22277)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
                              id bigint NOT NULL,
                              created_date timestamp without time zone,
                              last_modified_date timestamp without time zone,
                              version integer,
                              first_name character varying(255),
                              last_name character varying(255),
                              login character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 2886 (class 0 OID 22253)
-- Dependencies: 212
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.account (id, created_date, last_modified_date, version, initial_amount, premium_fee, standard_fee, account_number, account_type, amount, fee, users) FROM stdin;
1	2020-04-01 20:12:40.554	2020-04-01 20:12:40.554	0	1000.00	5.00	0.00	PL22111719974057557352916858	STANDARD	1000.00	5.00	1
2	2020-04-01 20:36:59.135	2020-04-01 20:36:59.135	0	1000.00	5.00	0.00	PL19111669314801740143170457	STANDARD	1000.00	5.00	2
3	2020-04-01 20:40:42.349	2020-04-01 20:40:42.349	0	1000.00	5.00	0.00	PL87111982662050996256928481	STANDARD	1000.00	5.00	3
4	2020-04-01 20:42:08.853	2020-04-01 20:42:08.853	0	1000.00	5.00	0.00	PL97111159641461244691631725	STANDARD	1000.00	5.00	4
5	2020-04-01 20:43:07.076	2020-04-01 20:43:07.076	0	1000.00	5.00	0.00	PL43111369798167176557167016	STANDARD	1000.00	5.00	1
6	2020-04-01 20:43:17.807	2020-04-01 20:43:17.807	0	1000.00	5.00	0.00	PL28111063107807732363304012	STANDARD	1000.00	5.00	2
7	2020-04-01 20:43:29.386	2020-04-01 20:43:29.386	0	1000.00	5.00	0.00	PL60111776007948994546599940	STANDARD	1000.00	5.00	4
8	2020-04-01 20:43:41.89	2020-04-01 20:43:41.89	0	1000.00	5.00	0.00	PL07111159950384005329505875	PREMIUM	1000.00	0.00	1
9	2020-04-01 20:43:45.887	2020-04-01 20:43:45.887	0	1000.00	5.00	0.00	PL07111211506358442400156552	PREMIUM	1000.00	0.00	3
10	2020-04-01 20:47:02.463	2020-04-01 20:47:02.463	0	1000.00	5.00	0.00	PL08111417987137412267636131	STANDARD	1000.00	5.00	5
\.


--
-- TOC entry 2877 (class 0 OID 22179)
-- Dependencies: 203
-- Data for Name: auction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auction (id, active, auction_owner_account_id, auction_owner_id, description, end_date, price, quantity, start_date, title) FROM stdin;
\.


--
-- TOC entry 2887 (class 0 OID 22261)
-- Dependencies: 213
-- Data for Name: card; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.card (id, created_date, last_modified_date, version, card_number, cvv, expiration_date, account_id) FROM stdin;
1	2020-04-01 20:42:30.789	2020-04-01 20:42:30.789	0	1234	123	2025-04-01	1
2	2020-04-01 20:42:34.872	2020-04-01 20:42:34.872	0	1234	123	2025-04-01	3
3	2020-04-01 20:44:25.769	2020-04-01 20:44:25.769	0	1234	123	2025-04-01	4
\.


--
-- TOC entry 2879 (class 0 OID 22195)
-- Dependencies: 205
-- Data for Name: cards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cards (id, created_date, last_modified_date, version, card_number, cvv, expiration_date, account_id) FROM stdin;
\.


--
-- TOC entry 2876 (class 0 OID 22161)
-- Dependencies: 202
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	Fill with some test data	SQL	V1__Fill_with_some_test_data.sql	-708032304	postgres	2020-04-01 20:11:13.961793	80	t
\.


--
-- TOC entry 2888 (class 0 OID 22269)
-- Dependencies: 214
-- Data for Name: transfer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transfer (id, created_date, last_modified_date, version, amount, auction_owner_account_id, auction_owner_id, auction_winner_account_number, title) FROM stdin;
\.


--
-- TOC entry 2889 (class 0 OID 22277)
-- Dependencies: 215
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, created_date, last_modified_date, version, first_name, last_name, login) FROM stdin;
1	2020-04-01 20:12:40.525	2020-04-01 20:12:40.525	0	Tomasz	Siemasz	tsiemasz
2	2020-04-01 20:36:59.119	2020-04-01 20:36:59.119	0	Jan	Pan	pjan
3	2020-04-01 20:40:42.336	2020-04-01 20:40:42.336	0	Anna	Kwarant	akwarant
4	2020-04-01 20:42:08.851	2020-04-01 20:42:08.851	0	Roman	Wesoły	rwesoly
5	2020-04-01 20:47:02.46	2020-04-01 20:47:02.46	0	Edward	Kredek	ekredek
\.


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 208
-- Name: account_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_sequence', 51, true);


--
-- TOC entry 2896 (class 0 OID 0)
-- Dependencies: 204
-- Name: auction_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auction_sequence', 1, false);


--
-- TOC entry 2897 (class 0 OID 0)
-- Dependencies: 209
-- Name: card_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.card_sequence', 51, true);


--
-- TOC entry 2898 (class 0 OID 0)
-- Dependencies: 206
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 2899 (class 0 OID 0)
-- Dependencies: 207
-- Name: order_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_sequence', 1, false);


--
-- TOC entry 2900 (class 0 OID 0)
-- Dependencies: 210
-- Name: transfer_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transfer_sequence', 1, false);


--
-- TOC entry 2901 (class 0 OID 0)
-- Dependencies: 211
-- Name: user_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_sequence', 51, true);


--
-- TOC entry 2739 (class 2606 OID 22260)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 2735 (class 2606 OID 22224)
-- Name: auction auction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auction
    ADD CONSTRAINT auction_pkey PRIMARY KEY (id);


--
-- TOC entry 2741 (class 2606 OID 22268)
-- Name: card card_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT card_pkey PRIMARY KEY (id);


--
-- TOC entry 2737 (class 2606 OID 22228)
-- Name: cards cards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_pkey PRIMARY KEY (id);


--
-- TOC entry 2732 (class 2606 OID 22169)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 2743 (class 2606 OID 22276)
-- Name: transfer transfer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transfer
    ADD CONSTRAINT transfer_pkey PRIMARY KEY (id);


--
-- TOC entry 2745 (class 2606 OID 22286)
-- Name: users uk_ow0gan20590jrb00upg3va2fn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login);


--
-- TOC entry 2747 (class 2606 OID 22284)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2733 (class 1259 OID 22170)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 2749 (class 2606 OID 22292)
-- Name: card fk8v67eys6tqflsm6hrdgru2phu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.card
    ADD CONSTRAINT fk8v67eys6tqflsm6hrdgru2phu FOREIGN KEY (account_id) REFERENCES public.account(id);


--
-- TOC entry 2748 (class 2606 OID 22287)
-- Name: account fkgw8m7sqofig3r2f5ugvxyyo92; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT fkgw8m7sqofig3r2f5ugvxyyo92 FOREIGN KEY (users) REFERENCES public.users(id);


-- Completed on 2020-04-01 20:47:42

--
-- PostgreSQL database dump complete
--

